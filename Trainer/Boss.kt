package Trainer

import Attacken.PokemonAttacke
import Pokemon.Pokemon
import Pokemon.pokemonList

class Boss(var bossPokemon:MutableList<Pokemon>): Trainer(bossPokemon){

  /*
    fun bossKampf (){
        for ( bossPokemon in bossPokemon ){
            while(bossPokemon.hp <= 0){
                println()
                trainerPokemon.forEach { playerPokemon ->
                    printLetterByLetter("${playerPokemon.name}, Level: ${playerPokemon.lvl}, HP: ${playerPokemon.hp}")
                    while (playerPokemon.hp > 0) {
                        pokemonList.shuffled().forEach { bossPokemon ->
                            println()
                            printLetterByLetter("Ein wildes ${bossPokemon.name}, Level: ${bossPokemon.lvl} ist aufgetaucht!")
                            println()
                            printLetterByLetter("Kampf zwischen ${playerPokemon.name} und Wildem ${bossPokemon.name}!")

                            while (playerPokemon.hp > 0 && bossPokemon.hp > 0) {
                                println()
                                printLetterByLetter("${bossPokemon.name}\nHP: ${bossPokemon.hp}")
                                println()
                                printLetterByLetter("${playerPokemon.name}\nHP: ${playerPokemon.hp}")
                                println()
                                printLetterByLetter("1. Attacke 2. Fliehen\n3. Items 4. Pokemon:")
                                try {
                                    when (readln().toInt()) {
                                        1 -> {
                                            printLetterByLetter("Whähle eine Attacke aus")
                                            println(playerPokemon.attacke)
                                            when (readln().toInt()) {
                                                1 -> {
                                                    var playerAttack = playerPokemon.attacke[0]
                                                    val damage = schaden(playerPokemon, bossPokemon, playerAttack)
                                                    println()
                                                    printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                                    bossPokemon.hp -= damage
                                                }

                                                2 -> {
                                                    var playerAttack = playerPokemon.attacke[1]
                                                    val damage = schaden(playerPokemon, bossPokemon, playerAttack)
                                                    println()
                                                    printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
                                                    bossPokemon.hp -= damage
                                                }

                                                else -> {println()
                                                    printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 2 eingeben")

                                                }
                                            }
                                        }

                                        2 -> {
                                            printLetterByLetter("Du kannst im Bosskampf nicht fliehen")
                                            bossPokemon.reset()

                                        }

                                        else -> printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 2 eingeben")
                                    }
                                } catch (e: Exception) {
                                    printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                }
                                if (bossPokemon.hp > 0) {
                                    val wildAttack = bossPokemon.attacke.random()
                                    val damage = schaden(bossPokemon, playerPokemon, wildAttack)
                                    println()
                                    printLetterByLetter("Rivales Pokemon ${bossPokemon.name} setzt $wildAttack ein und fügt $damage Schaden zu!")
                                    playerPokemon.hp -= damage
                                    println()
                                    printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp} HP")
                                    println()
                                    printLetterByLetter("Rivales Pokemon hat noch  ${bossPokemon.name} hat noch ${bossPokemon.hp} HP")
                                }
                            }
                            try {
                                if (playerPokemon.hp < 0) {
                                    println()
                                    printLetterByLetter("${playerPokemon.name} wurde besiegt!")
                                    println()
                                    println("Möchtest du es nochmal versuchen? (ja/nein)")
                                    val input = readln()
                                    if (input in setOf("ja", "j", "yes", "y")) {
                                        trainerPokemon.clear()
                                        playerPokemon.reset()
                                        bossPokemon.reset()

                                    } else  {
                                    }
                                } else {
                                    println()
                                    printLetterByLetter("Du hast dein Rivalen Besiegt")
                                    playerPokemon.lvlUp()
                                    bossPokemon.lvlUp()

                                }
                            } catch (e: Exception) {
                                printLetterByLetter("Ungültige Eingabe")
                            }
                        }
                    }
                }
            }
        }

    }


   override fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        val basisSchaden = attackName.schaden
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }

   */

}