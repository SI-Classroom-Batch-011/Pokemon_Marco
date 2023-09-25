package Kanto

import Attacken.PokemonAttacke
import Pokemon.Pokemon
import Pokemon.pokemonList
import PokemonTypen.PokemonType

private var glumanda =
    Pokemon(
        "Glumanda",
        PokemonType.FEUER,
        99,
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
private var trainerPokemon = mutableListOf<Pokemon>()
private var continueGame = true

// Die welt in dem sich alles abspielen wird
class Kanto() {

    // Funktioniert muss noch schöner gemacht werden
    fun pokemonAuswahl() {
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

    // Kampf funktioniert fliehen funktioniert attacken auswahl funktioniert muss aber noch bearbeitet werden
    // wenn das pokemon tot ist läuft das program  weiter wenn man einen andere eingabe macht
    // pokemon auswahl muss noch eingefügt werden und items
    fun kampfWildePokemon() {
        println()
        trainerPokemon.forEach { playerPokemon ->
            printLetterByLetter("${playerPokemon.name}, Level: ${playerPokemon.lvl}, HP: ${playerPokemon.hp}")
            pokemonList.shuffled().forEach { wildePokemon ->
                println()
                printLetterByLetter("Ein wildes ${wildePokemon.name}, Level: ${wildePokemon.lvl} ist aufgetaucht!")
                println()
                printLetterByLetter("Kampf zwischen ${playerPokemon.name} und Wildem ${wildePokemon.name}!")
                while (playerPokemon.hp > 0 && wildePokemon.hp > 0) {

                    while (playerPokemon.hp > 0 && wildePokemon.hp > 0) {
                        println()
                        printLetterByLetter("${wildePokemon.name}\nHP: ${wildePokemon.hp}")
                        println()
                        printLetterByLetter("${playerPokemon.name}\nHP: ${playerPokemon.hp} ${playerPokemon.lvl}")
                        println()
                        println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")

                        var validInput = false

                        while (!validInput) {
                            try {
                                when (readln().toInt()) {
                                    1 -> {
                                        printLetterByLetter("Whähle eine Attacke aus")
                                        println(playerPokemon.attacke)
                                        when (readln().toInt()) {
                                            1 -> {
                                                var playerAttack = playerPokemon.attacke[0]
                                                val damage = schaden(playerPokemon, wildePokemon, playerAttack)
                                                println()
                                                printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                                wildePokemon.hp -= damage
                                                validInput= true
                                            }

                                            2 -> {
                                                var playerAttack = playerPokemon.attacke[1]
                                                val damage = schaden(playerPokemon, wildePokemon, playerAttack)
                                                println()
                                                printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                                wildePokemon.hp -= damage
                                                validInput = true
                                            }


                                            else -> {
                                                println()
                                                println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                                println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                                                validInput = false
                                            }
                                        }
                                    }

                                    2 -> {
                                        printLetterByLetter("Du bist geflohen!")
                                        wildePokemon.reset()
                                        validInput = true
                                        moveInRegion()
                                    }
                                    else -> {
                                        println()
                                        println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                        println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                                        validInput = false
                                    }
                                }
                            } catch (e: Exception) {
                                println()
                                printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                                validInput = false
                            }
                        }

                        if (wildePokemon.hp > 0) {
                            val wildAttack = wildePokemon.attacke.random()
                            val damage = schaden(wildePokemon, playerPokemon, wildAttack)
                            println()
                            printLetterByLetter("Wildes ${wildePokemon.name} setzt $wildAttack ein und fügt $damage Schaden zu!")
                            playerPokemon.hp -= damage
                            println()
                            printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp} HP")
                            println()
                            printLetterByLetter("Wildes ${wildePokemon.name} hat noch ${wildePokemon.hp} HP")
                        }
                    }
                    try {
                        if (playerPokemon.hp < 0) {

                            println()
                            printLetterByLetter("${playerPokemon.name} wurde besiegt!")
                            println()
                            println("Möchtest du nochmal spielen? (ja/nein)")
                            val input = readln()
                            if (input in setOf("ja", "j", "yes", "y")) {
                                trainerPokemon.clear()
                                playerPokemon.reset()
                                wildePokemon.reset()
                                pokemonAuswahl()

                            } else if (playerPokemon.lvl == 100) {
                                println("gewonnen")
                                return
                            } else {
                                return
                            }
                        } else {
                            println()
                            printLetterByLetter("Wildes ${wildePokemon.name} wurde besiegt!")
                            playerPokemon.lvlUp()
                            wildePokemon.lvlUp()
                            moveInRegion()
                        }
                    } catch (e: Exception) {
                        printLetterByLetter("Ungültige Eingabe")
                    }
                }
            }
        }
    }


    // funktioniert
    fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        val basisSchaden = attackName.schaden
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }

    // Das laufen wird simuliert und es taucht ein zufälliges pokemon
    fun moveInRegion() {
        println()
        var steps = 0
        val stepsUntilWildPokemonAppears = (10..50).random()
        for (playerPokemon in trainerPokemon)

            while (continueGame) {

                steps += (10..20).random()
                if (steps % stepsUntilWildPokemonAppears == 0) {
                    kampfWildePokemon()
                } else if (playerPokemon.lvl == 100) {
                    return println("Gewonnen")
                    continueGame = false

                } else if (playerPokemon.hp < 0)
                    return
                else {
                    printLetterByLetter(".")
                }
            }
        println()
    }


    // muss bearbeitet werden oder verlagert werden damit man mit einem pokeball das pokemon einfangen kann
    fun pokemonFangen(wildPokemon: Pokemon, erfolgschance: Int) {
        println("Du versuchst, das wilde ${wildPokemon.name} zu fangen!")

        if (erfolgschance >= 0 && erfolgschance <= 100) {
            val fangchance = (0..100).random()

            if (fangchance < erfolgschance) {
                println("Erfolgreich! Du hast ${wildPokemon.name} gefangen")

                trainerPokemon.add(wildPokemon)
            } else {
                println("Leider nicht erfolgreich. ${wildPokemon.name} ist entkommen")
            }
        }
    }

    // Muss noch bearbeitet werden
    fun pokemonEntwicklung() {
        for (playerPokemon in trainerPokemon) if (playerPokemon.lvl == 16) {

        }
    }

    fun restartGame() {}
    fun printLetterByLetter(text: String) {
        for (char in text) {
            print(char)
            Thread.sleep(50)
        }
    }

    fun game() {
        pokemonAuswahl()
        moveInRegion()

    }

    fun newGame(): Boolean {
        trainerPokemon.clear()

        println("Willst du nochmal Zocken? (ja/nein): ")
        val entscheidung = readln().trim().lowercase()
        Thread.sleep(1000)

        when (entscheidung) {
            "ja", "j", "yes", "y" -> {
                println("Noch eine Runde 👍")
                pokemonAuswahl()
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
        return null == true
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



}