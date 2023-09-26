package Items

import Kanto.trainerPokemon

import Pokemon.Pokemon

open class Pokeball : Items() {
    fun pokemonFangen(wildPokemon: Pokemon, erfolgschance: Int): Boolean {
        println("Du versuchst, das wilde ${wildPokemon.name} zu fangen!")

        if (erfolgschance >= 0 && erfolgschance <= 100) {
            val fangchance = (0..100).random()

            if (fangchance < erfolgschance) {
                if (trainerPokemon.size <= 4) {
                    println("Erfolgreich! Du hast ${wildPokemon.name} gefangen")
                    trainerPokemon.add(wildPokemon)
                } else {
                    println("Du hast zu viele Pokemon")
                    // pokemonAustauchen()
                }
                return true
            } else {
                println("Leider nicht erfolgreich. ${wildPokemon.name} ist entkommen")
            }
        }

        return false
    }
}

fun pokemonAustauschen() {

}