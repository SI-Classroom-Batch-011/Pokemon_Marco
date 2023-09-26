package Kanto

import Attacken.PokemonAttacke
import Items.Heiler
import Items.Pokeball
import Pokemon.Pokemon
import Pokemon.pokemonList
import PokemonTypen.PokemonType
import Trainer.Boss

private var glumanda =
    Pokemon(
        "Glumanda",
        PokemonType.FEUER,
        98,
        100,
        100,
        100,
        mutableListOf(PokemonAttacke.Kratzer, PokemonAttacke.Glut)
    )
private var shiggy = Pokemon(
    "Schiggy", PokemonType.WASSER, 10, 44, 48, 65, mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Aquaknarre)
)
private var bisasam =
    Pokemon("Bisasam", PokemonType.PFLANZE, 10, 45, 49, 49, mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Absorb))

var trainerPokemon: MutableList<Pokemon> = mutableListOf()

// Die welt in dem sich alles abspielen wird
open class Kanto() {


    val continueGame: Boolean
        get() {
            return trainerPokemon.any { it.hp >= 0 }
        }

    var boss: Boss = Boss(
        mutableListOf(
            Pokemon(
                "Mewtu",
                PokemonType.PSYCHO,
                1,
                106,
                110,
                90,
                mutableListOf(
                    PokemonAttacke.Konfusion,
                    PokemonAttacke.Psychokinese,
                    PokemonAttacke.Psychoschock,
                    PokemonAttacke.Barriere
                )
            )
        )
    )


    var pokemonListe: MutableList<Pokemon> = pokemonList.shuffled().toMutableList()

    // Funktioniert muss noch schöner gemacht werden
    fun starterPokemonAuswahl() {
        var validInput = false
        var starterPokemon = mutableListOf<Pokemon>(glumanda, shiggy, bisasam)

        printLetterByLetter("Willkommen in der Pokemon Welt ")

        println()
        printLetterByLetter("Um in der Welt der Pokemon etwas zu erreichen musst du dir erst ein Starterpokemon aussuchen du hast die Auswahl zwischen : ")
        println()

        for (pokemon in starterPokemon) {
            println(pokemon)
            Thread.sleep(1000)
        }
        while (!validInput) {
            try {
                printLetterByLetter("1 für : ${glumanda.name}\n2 für : ${shiggy.name}\n3 für :${bisasam.name}")
                println()
                Thread.sleep(500)


                println("Wähle ein Pokemon aus gebe eine Zahl von 1 bis 3 ein: ")

                var input = readln().toInt()

                when (input) {
                    1 -> trainerPokemon.add(starterPokemon[input - 1])
                    2 -> trainerPokemon.add(starterPokemon[input - 1])
                    3 -> trainerPokemon.add(starterPokemon[input - 1])


                }
                return printLetterByLetter("Du hast dich für ${starterPokemon[input - 1].name} entschieden")

            } catch (e: Exception) {
                println("Falsche Eingabe Versuche es nochmal")
                validInput = false
            }
        }
    }

    // Kampf
    fun kampfWildePokemon() {

        var wildePokemon = pokemonListe.random()
        var playerPokemon = trainerPokemon.first()
        println()
        printLetterByLetter("${playerPokemon.name}, Level: ${playerPokemon.lvl}, HP: ${playerPokemon.hp}\n")
        printLetterByLetter("Ein wildes ${wildePokemon.name}, Level: ${wildePokemon.lvl} ist aufgetaucht!\n")
        printLetterByLetter("Kampf zwischen ${playerPokemon.name} und Wildem ${wildePokemon.name}!\n")

        while (wildePokemon.hp >= 0 && trainerPokemon.any { it.hp > 0 }) {


            println()
            printLetterByLetter("${wildePokemon.name}\nHP: ${wildePokemon.hp}")
            println()
            printLetterByLetter("${playerPokemon.name}\nHP: ${playerPokemon.hp} ${playerPokemon.lvl}")
            println()


            var validInput = false

            while (!validInput) {
                try {
                    println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                    var input = readln().toInt()
                    when (input) {
                        1 -> {
                            printLetterByLetter("Whähle eine Attacke aus:\n")
                            playerPokemon.attacke.forEachIndexed { i, attack ->
                                println("${i + 1}: $attack")
                            }
                            var input = readln().toInt()
                            if (input <= playerPokemon.attacke.size) {
                                attackeAuswahl(playerPokemon, wildePokemon, input - 1)
                                validInput = true
                            } else {
                                println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                validInput = false
                            }

                        }

                        2 -> {
                            printLetterByLetter("Du bist geflohen!")
                            bewegung()
                            return
                        }

                        3 -> {
                            printLetterByLetter("1. Heiler 2. Pokéball\nWähle ein Item aus der Item Box:\n")
                            var input = readln().toInt()
                            when (input) {
                                1 -> {
                                    Heiler().heilen(playerPokemon)
                                    println()
                                    printLetterByLetter("${playerPokemon.name} wurde um ${playerPokemon.hp - playerPokemon.standartHp} geheilt")
                                }

                                2 -> {
                                    val fangchance = (10..100).random()
                                    Pokeball().pokemonFangen(wildePokemon, fangchance)
                                    return

                                }

                                else -> {
                                    println()
                                    println("Ungültige Eingabe! Bitte eine Zahl eingeben:")
                                    continue
                                }
                            }
                            validInput = true
                        }

                        4 -> {
                            playerPokemon = pokemonAuswahl()
                            validInput = true
                        }

                        else -> {
                            println()
                            println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                            validInput = false
                        }
                    }
                } catch (e: Exception) {
                    println()
                    println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                    validInput = false
                }
            }

            if (wildePokemon.hp > 0) {
                wildePokemonAttacke(wildePokemon,playerPokemon)
            }

            if (playerPokemon.hp < 0) {
                printLetterByLetter("${playerPokemon.name} wurde besiegt!\n")
                playerPokemon = pokemonAuswahl()
            }
        }// kampf zu ende
        if (wildePokemon.hp < 0) {
            println()
            printLetterByLetter("Wildes ${wildePokemon.name} wurde besiegt!\n")
            playerPokemon.lvlUp()
            wildePokemon.lvlUp()
        }
    }


    // funktioniert Schaden wird berechnet
    open fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        val basisSchaden = attackName.schaden
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }

    // Das laufen wird simuliert und es taucht ein zufälliges pokemon
    fun bewegung() {
        println("Läuft Durch die Welt")
        println()
        var steps = 0
        val stepsUntilWildPokemonAppears = (10..50).random()


        do {
            printLetterByLetter(".")
            steps += (10..20).random()
            if (steps % stepsUntilWildPokemonAppears == 0) {
                kampfWildePokemon()
            } else if (trainerPokemon.any { it.lvl >= 100 }) {
                println("Bosskampf")
                return

            }
        } while (continueGame)
    }

    // Muss noch bearbeitet werden

    fun printLetterByLetter(text: String) {
        for (char in text) {
            print(char)
            Thread.sleep(10)
        }
    }

    // Das Spiel
    fun game() {
        //TODO(): while game not gameover
        do {
            starterPokemonAuswahl()
            bewegung()
            boss.bossKampf()
        } while (newGame())


    }

    // Neu Start des spiels
    fun newGame(): Boolean {
        var playerPokemon = trainerPokemon
        println("Willst du nochmal Zocken? (ja/nein): ")
        val entscheidung = readln().trim().lowercase()
        Thread.sleep(1000)

        // reset werte und liste
        when (entscheidung) {
            "ja", "j", "yes", "y" -> {
                println("Noch eine Runde 👍")
                glumanda.reset()
                shiggy.reset()
                bisasam.reset()
                playerPokemon.clear()
                return true
            }

            "nein", "n", "no" -> {
                println("Danke fürs Zocken!!")
                return false
            }

            else -> {
                println("❌Ungültige Eingabe!!!❌ Bitte ja oder nein eingeben")
                return newGame()
            }
        }
    }

    fun indexEinlesen(min: Int, max: Int): Int {
        var index: Int

        do {
            try {
                println("Geben sie einen Index ein: (von $min bis $max)")
                index = readln().toInt()
            } catch (ex: Exception) {
                println("Sie haben einen Ungültigen Index eingegeben")
                println("Probieren sie es nochmal")
                index = -1000
                continue
            }
            //...

        } while (index !in min..max)

        return index
    }

    // Pokemon auswahl
    fun pokemonAuswahl(): Pokemon {
        println("Wähle ein Pokemon aus:")
        trainerPokemon.forEachIndexed { index, pokemon ->
            println("${index + 1}. ${pokemon.name}")
        }
        try {
            val selectedIndex = readln().toInt() - 1
            if (selectedIndex >= 0 && selectedIndex < trainerPokemon.size) {

                val selectedPokemon = trainerPokemon[selectedIndex]
                println()
                printLetterByLetter("${selectedPokemon.name}, ich wähle dich!")
                return selectedPokemon
            } else {
                println()
                printLetterByLetter("Ungültige Auswahl! Bitte eine Zahl ein von 1 bis ${trainerPokemon.size}")
            }
        } catch (e: Exception) {
            println()
            printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl eingeben")
        }
        return pokemonAuswahl()
    }

    // Attacken auswahl
    fun attackeAuswahl(playerPokemon: Pokemon, wildePokemon: Pokemon, attackeIndex: Int) {
        var playerAttack = playerPokemon.attacke[attackeIndex]
        val damage = schaden(playerPokemon, wildePokemon, playerAttack)
        println()
        printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
        wildePokemon.hp -= damage
    }
    fun wildePokemonAttacke(wildePokemon: Pokemon , playerPokemon: Pokemon){
        val wildAttack = wildePokemon.attacke.random()
        val damage = schaden(wildePokemon, playerPokemon, wildAttack)
        println()
        printLetterByLetter("Wildes ${wildePokemon.name} setzt $wildAttack ein und fügt $damage Schaden zu!\n")
        playerPokemon.hp -= damage
        println()
        printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp} HP\n")
        println()
        printLetterByLetter("Wildes ${wildePokemon.name} hat noch ${wildePokemon.hp} HP\n")
    }
}