package Trainer


import Attacken.PokemonAttacke
import Pokemon.Pokemon
import Pokemon.pokemonList
import Kanto.trainerPokemon

// wird eventueller boss werden


open class Trainer(var trainerPokemon: MutableList<Pokemon>) {

    fun printLetterByLetter(text: String) {
        for (char in text) {
            print(char)
            Thread.sleep(50)
        }
    }


}