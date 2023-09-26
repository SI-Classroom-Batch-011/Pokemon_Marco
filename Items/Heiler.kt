package Items
import Pokemon.Pokemon

open class Heiler() : Items() {

   open fun heilen(pokemon: Pokemon) {
       println(pokemon.standartHp - pokemon.hp)
       pokemon.hp = pokemon.standartHp
    }
}