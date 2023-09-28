package Items

import Pokemon.Pokemon

// Klasse für den Heiler
open class Heiler() : Items() {

    open fun heilen(pokemon: Pokemon) {
        // Heilt das Pokemon auf die Standartwerte
        pokemon.hp = pokemon.standartHp
    }
}

// Zukunftsfunktion
/*
fun pokemonAuswahl
 */