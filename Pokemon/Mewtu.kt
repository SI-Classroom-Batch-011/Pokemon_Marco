package Pokemon

import Attacken.PokemonAttacke

open class Mewtu() : Pokemon(
    "Mewtu", PokemonType.PSYCHO,
    100,
    400,
    110,
    90,
    mutableListOf(PokemonAttacke.Hyperstrahl),

) {
    fun mewtuAttacke() {
        var schaden = (60..80).random()

    }
}