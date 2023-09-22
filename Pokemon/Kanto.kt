package Pokemon

import Attacken.PokemonAttacke
import Trainer.Trainer

private var glumanda =
    Pokemon("Glumanda", PokemonType.FEUER, 10, 39, 52, 43, mutableListOf(PokemonAttacke.Kratzer, PokemonAttacke.Glut))
private var shiggy = Pokemon(
    "Schiggy",
    PokemonType.WASSER,
    10,
    44,
    48,
    65,
    mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Aquaknarre)
)
private var bisasam =
    Pokemon("Bisasam", PokemonType.PFLANZE, 10, 45, 49, 49, mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Absorb))
var trainerPokemon = mutableListOf<Pokemon>()


class Kanto(var wildePokemon: MutableList<Pokemon>) {

    fun pokemonAuswahl() {
        try {
            var starterPokemon = mutableListOf<Pokemon>(glumanda, shiggy, bisasam)

            for (pokemon in starterPokemon) {
                println(pokemon)
                Thread.sleep(1000)
            }
            println("1:${glumanda.name}\n2:${shiggy.name}\n3:${bisasam.name}")
            println("Wähle ein Pokemon aus gebe eine Zahl von 1-3 ein: ")

            var input = readln().toInt()

            when (input) {
                1 -> trainerPokemon.add(starterPokemon[input - 1])
                2 -> trainerPokemon.add(starterPokemon[input - 1])
                3 -> trainerPokemon.add(starterPokemon[input - 1])


            }
            return println("Du hast dich für ${starterPokemon[input - 1].name} entschieden")

        } catch (e: Exception) {
            println("Falsche Eingabe Versuche es nochmal")
            pokemonAuswahl()
        }
    }

    fun kampfWildePokemon() {
        trainerPokemon.forEach { playerPokemon ->
            println("${playerPokemon.name}, Level: ${playerPokemon.lvl}, HP: ${playerPokemon.hp}")
            while (playerPokemon.hp > 0) {
                for (wildePokemon in wildePokemon) {
                    println("Ein wildes ${wildePokemon.name}, Level: ${wildePokemon.lvl} ist aufgetaucht!")
                    println("Kampf zwischen ${playerPokemon.name} und wildem ${wildePokemon.name}!")

                    while (playerPokemon.hp > 0 && wildePokemon.hp > 0) {
                        println("${wildePokemon.name}\nHP: ${wildePokemon.hp}")
                        println("${playerPokemon.name}\nHP: ${playerPokemon.hp}")

                        println("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                        try {
                            when (readln().toInt()) {
                                1 -> {
                                    println("Whähle eine Attacke aus")
                                    println(playerPokemon.attacke)
                                    when (readln().toInt()) {
                                        1 -> {
                                            var playerAttack = playerPokemon.attacke[0]
                                            val damage = schaden(playerPokemon, wildePokemon, playerAttack)
                                            println("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                            wildePokemon.hp -= damage
                                        }

                                        2 -> {
                                            var playerAttack = playerPokemon.attacke[1]
                                            val damage = schaden(playerPokemon, wildePokemon, playerAttack)
                                            println("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                            wildePokemon.hp -= damage
                                        }

                                    }


                                }

                                2 -> {
                                    println("Du bist geflohen!")
                                    pokemonList.shuffled()

                                }

                                else -> println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 4 eingeben")
                            }
                        } catch (e: Exception) {
                            println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                        }
                        if (wildePokemon.hp > 0) {
                            val wildAttack = wildePokemon.attacke.random()
                            val damage = schaden(wildePokemon, playerPokemon, wildAttack)
                            println("Wildes ${wildePokemon.name} setzt $wildAttack ein und fügt $damage Schaden zu!")
                            playerPokemon.hp -= damage
                            println("${playerPokemon.name} hat noch ${playerPokemon.hp} HP")
                            println("Wildes ${wildePokemon.name} hat noch ${wildePokemon.hp} HP")
                        }
                    }
                    if (playerPokemon.hp <= 0) {
                        println("${playerPokemon.name} wurde besiegt!")
                        println("Möchtest du nochmal spielen:")
                        val input = readln()
                        if (input in setOf("ja", "j", "yes", "y")) {
                            trainerPokemon.clear()
                            playerPokemon.reset()
                            wildePokemon.reset()
                            moveInRegion()
                        } else
                            return println("Bye")
                    }
                    if (wildePokemon.hp <= 20) {
                        pokemonFangen(wildePokemon, (15..100).random())
                        wildePokemon.hp = wildePokemon.standartHp
                        println(wildePokemon.toString())

                    } else {
                        println("Wildes ${wildePokemon.name} wurde besiegt!")
                        playerPokemon.lvlUp()

                    }


                }
            }
        }
    }

    fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        val basisSchaden = attackName.schaden
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }

    fun moveInRegion() {
       var continueGame = true
       pokemonAuswahl()
       var steps = 0
       val stepsUntilWildPokemonAppears = (10..50).random() // Anzahl der Schritte, bis ein wildes Pokémon auftaucht

       while (continueGame) {
           steps += (10..20).random()

           if (steps % stepsUntilWildPokemonAppears == 0) {
               println("Ein wildes Pokémon ist aufgetaucht!")
               kampfWildePokemon()
           } else {
               println("Du bist weitergelaufen, aber kein wildes Pokémon ist aufgetaucht.")
           }
       }
   }




    /*fun moveInRegion() {
        var continueGame = true
        pokemonAuswahl()
        while (continueGame) {
            kampfWildePokemon()
            for (trainerPokemon in trainerPokemon) {
                if (trainerPokemon.lvl == 100)
                    println("Du hast Gewonnen")
            }

            println("Möchtest du weiterlaufen? (j/n)")
            val input = readln()
            if (input !in setOf("ja", "j", "yes", "y")) {
                continueGame = false
                println("Läuft.\nLäuft..\nLäuft...")
            }

        }
    }

     */

    fun pokemonFangen(wildPokemon: Pokemon, erfolgschance: Int) {
        println("Du versuchst, das wilde ${wildPokemon.name} zu fangen!")

        if (erfolgschance >= 0 && erfolgschance <= 100) {
            val fangchance = (0..100).random() // Zufällige Zahl zwischen 0 und 100

            if (fangchance < erfolgschance) {
                println("Erfolgreich! Du hast ${wildPokemon.name} gefangen")

                trainerPokemon.add(wildPokemon)
            } else {
                println("Leider nicht erfolgreich. ${wildPokemon.name} ist entkommen")
            }
        }
    }

    fun getPokemonByLevelRange(minLevel: Int, maxLevel: Int): List<Pokemon> {
        return pokemonList.filter { it.lvl in minLevel..maxLevel }
    }
}
/*
   fun moveInRegion() {
       var continueGame = true
       pokemonAuswahl()
       var steps = 0
       val stepsUntilWildPokemonAppears = 10 // Anzahl der Schritte, bis ein wildes Pokémon auftaucht

       while (continueGame) {
           steps++

           if (steps % stepsUntilWildPokemonAppears == 0) {
               println("Ein wildes Pokémon ist aufgetaucht!")
               kampfWildePokemon()
           } else {
               println("Du bist weitergelaufen, aber kein wildes Pokémon ist aufgetaucht.")
           }

           println("Möchtest du weiterlaufen? (j/n)")
           val input = readln()

           if (input !in setOf("ja", "j", "yes", "y")) {
               continueGame = false
           }

           println("Läuft.\nLäuft..\nLäuft...")
       }
   }

    */