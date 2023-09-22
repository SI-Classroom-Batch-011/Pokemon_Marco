import Pokemon.*
import Trainer.Trainer

var mewtu : Mewtu = Mewtu()


fun main() {

    var trainer = Trainer(mutableListOf()).trainerPokemon
    var kanto = Kanto(pokemonList.shuffled().toMutableList())
    kanto.moveInRegion()


}