package Trainer

import Attacken.PokemonAttacke
import Items.Heiler
import Items.Pokeball
import Pokemon.Pokemon
import Pokemon.pokemonList
import PokemonTypen.PokemonType

class Boss(var bossPokemon:MutableList<Pokemon> = mutableListOf(Pokemon(
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
    )), Pokemon(
    "Dragoran",
    PokemonType.DRACHE,
    1,
    91,
    134,
    95,
    mutableListOf(
        PokemonAttacke.Donnerwelle,
        PokemonAttacke.Ruckzuckhieb,
        PokemonAttacke.Donner,
        PokemonAttacke.Wutanfall
    )
),Pokemon(
    "Arktos",
    PokemonType.EIS,
    50,
    90,
    85,
    100,
    mutableListOf(PokemonAttacke.Eisstrahl, PokemonAttacke.Blizzard, PokemonAttacke.Flügelschlag)
),Pokemon(
    "Lavados",
    PokemonType.FEUER,
    50,
    90,
    100,
    90,
    mutableListOf(
        PokemonAttacke.Glut,
        PokemonAttacke.Feuerwirbel,
        PokemonAttacke.Flammenwurf,
        PokemonAttacke.Flügelschlag
    )
)
)): Trainer(name = "Rivale",bossPokemon){

    fun bossKampf() {

        var bossPokemons = bossPokemon.random()
        var playerPokemon = Kanto.trainerPokemon.first()
        println()
        printLetterByLetter("${playerPokemon.name}, Level: ${playerPokemon.lvl}, HP: ${playerPokemon.hp}\n")
        printLetterByLetter("Rivale setzt ${bossPokemons.name}, Level: ${bossPokemons.lvl} ein\n")
        println()
        printLetterByLetter("Kampf zwischen ${playerPokemon.name} und ${bossPokemons.name}!\n")
        while (bossPokemon.any{it.hp > 0} && Kanto.trainerPokemon.any { it.hp > 0 }) {


            println()
            printLetterByLetter("${bossPokemons.name}\nHP: ${bossPokemons.hp}")
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
                            if(input<=playerPokemon.attacke.size){
                                attackeAuswahl(playerPokemon,bossPokemons,input-1)
                                validInput = true
                            }
                            else{
                                println("Ungültige Eingabe! Bitte eine Zahl eingeben")
                                validInput = false
                            }

                        }

                        2 -> {
                            printLetterByLetter("Du Kannst nicht Fliehen")
                            validInput = false
                        }

                        3 -> {
                            printLetterByLetter("1. Heiler 2. Pokéball\nWähle ein Item aus der Item Box:")
                            println()
                            var input = readln().toInt()
                            when (input) {
                                1 -> {
                                    Heiler().heilen(playerPokemon)
                                    println()
                                    printLetterByLetter("${playerPokemon.name} wurde um ${playerPokemon.hp - playerPokemon.standartHp} geheilt")
                                }

                                2 -> {
                                    println("Du kannst keine bosspokemon fangen")
                                    continue
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

            if (bossPokemons.hp > 0) {
                val wildAttack = bossPokemons.attacke.random()
                val damage = schaden(bossPokemons, playerPokemon, wildAttack)
                println()
                printLetterByLetter("Wildes ${bossPokemons.name} setzt $wildAttack ein und fügt $damage Schaden zu!")
                playerPokemon.hp -= damage
                println()
                printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp} HP")
                println()
                printLetterByLetter("Wildes ${bossPokemons.name} hat noch ${bossPokemons.hp} HP")
            }

            if (playerPokemon.hp < 0) {
                printLetterByLetter("${playerPokemon.name} wurde besiegt!")
                playerPokemon = pokemonAuswahl()
            }
        }// kampf zu ende
        if (bossPokemons.hp < 0) {
            println()
            printLetterByLetter("Wildes ${bossPokemons.name} wurde besiegt!\n")

        }
    }


   fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        val basisSchaden = attackName.schaden
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }
    fun printLetterByLetter(text: String) {
        for (char in text) {
            print(char)
            Thread.sleep(50)
        }
    }
    fun pokemonAuswahl():Pokemon{
        println("Wähle ein Pokemon aus:")
        Kanto.trainerPokemon.forEachIndexed { index, pokemon ->
            println("${index + 1}. ${pokemon.name}")
        }
        try {
            val selectedIndex = readln().toInt() - 1
            if (selectedIndex >= 0 && selectedIndex < Kanto.trainerPokemon.size) {

                val selectedPokemon = Kanto.trainerPokemon[selectedIndex]
                println()
                printLetterByLetter("${selectedPokemon.name}, ich wähle dich!")
                return selectedPokemon
            } else {
                println()
                printLetterByLetter("Ungültige Auswahl! Bitte eine Zahl zwischen 1 und ${Kanto.trainerPokemon.size} eingeben")
            }
        } catch (e: Exception) {
            println()
            printLetterByLetter("Ungültige Eingabe! Bitte eine Zahl eingeben")
        }
        return pokemonAuswahl()
    }
    fun attackeAuswahl(playerPokemon:Pokemon,bossPokemons:Pokemon,attackeIndex:Int){
        var playerAttack = playerPokemon.attacke[attackeIndex]
        val damage = schaden(playerPokemon, bossPokemons, playerAttack)
        println()
        printLetterByLetter("${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu!")
        bossPokemons.hp -= damage
    }
}