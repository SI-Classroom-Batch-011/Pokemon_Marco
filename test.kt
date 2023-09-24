import Attacken.PokemonAttacke
import Kanto.Kanto
import Pokemon.*
import PokemonTypen.PokemonType
import Trainer.Boss



fun main() {
    var boss = Boss(mutableListOf(Pokemon(
        "Mew",
        PokemonType.PSYCHO,
        1,
        40,
        30,
        30,
        mutableListOf(
            PokemonAttacke.Donnerschock,
            PokemonAttacke.Blizzard,
            PokemonAttacke.Flammenwurf,
            PokemonAttacke.Surfer
        )
    )))
    var kanto = Kanto()

    kanto.game()



}