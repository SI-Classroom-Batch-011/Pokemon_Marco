package Pokemon

import Attacken.PokemonAttacke
import Kanto.trainerPokemon
import PokemonTypen.PokemonType

// Pokemon und ihre Eigenschaften
open class Pokemon(
    val name: String,
    val typ: PokemonType,
    var lvl: Int,
    var hp: Int,
    var atk: Int,
    var def: Int,
    var attacke: MutableList<PokemonAttacke>

) {
    var canEvolve: Boolean = true
    var evolveLvl: Int = 0

    // Zukunftsconstruktor
    constructor(
        name: String,
        typ: PokemonType,
        lvl: Int,
        hp: Int,
        atk: Int,
        def: Int,
        attacke: MutableList<PokemonAttacke>, canEvolve: Boolean, evolveLvl: Int
    ) : this(name, typ, lvl, hp, atk, def, attacke) {
        this.canEvolve = canEvolve
        this.evolveLvl = evolveLvl
    }

    // Standard Werte
    var standartHp = hp
    var standartAtk = atk
    var standartDef = def
    var standartLvl = lvl

    /**
     * Diese Funktion gibt die Geräusche eines Pokémon aus, wobei der Name des Pokémon in Großbuchstaben wiederholt wird.
     */
    fun pokemonGeraeusche() {
        // Ausgabe der Geräusche des Pokémon mit wiederholtem Namen in Großbuchstaben
        println("${name.uppercase()}‼️,${name.uppercase()}‼️\n")
    }

    /**
     * Diese Methode überschreibt die standardmäßige `toString()`-Methode und erstellt eine Zeichenfolge, die die wichtigsten Eigenschaften
     * eines Pokémon in übersichtlicher Form darstellt.
     *
     * @return Eine Zeichenfolge, die die Eigenschaften des Pokémon enthält.
     */
    override fun toString(): String {
        // Erstellung der Zeichenfolge, die die Eigenschaften des Pokémon darstellt
        return "Name:${this.name}\n" +
                "Typ:${this.typ}\n" +
                "Level:${this.lvl}\n" +
                "Lebenspunkte:${this.hp}\n" +
                "Angriffskraft:${this.atk}\n" +
                "Verteidigungskraft:${this.def}\n" +
                "Attaken:${this.attacke}\n"
    }

    /**
     * Diese Methode erhöht das Level eines Pokémon und passt seine Eigenschaften entsprechend an.
     *
     * Das Pokémon erhält eine Erhöhung der maximalen Lebenspunkte (HP), Angriffskraft (ATK) und Verteidigungskraft (DEF).
     * Außerdem wird das Level um 5 erhöht.
     */
    open fun lvlUp() {
        // Aktuelle HP auf 0 setzen
        hp = 0

        // Maximal HP um 5 erhöhen
        standartHp += 10

        // HP auf das neue Maximalwert setzen
        hp = standartHp

        // Angriffskraft (ATK) um 2 erhöhen
        atk += 10

        // Verteidigungskraft (DEF) um 2 erhöhen
        def += 10

        // Level um 5 erhöhen
        lvl += 10
    }

    /**
     * Diese Methode setzt die Eigenschaften eines Pokémon auf ihre Standardwerte zurück.
     *
     * Dies umfasst die Zurücksetzung der Lebenspunkte (HP), Angriffskraft (ATK), Verteidigungskraft (DEF) und des Levels (LVL) auf ihre
     * ursprünglichen Standardwerte.
     */
    open fun reset() {
        // Aktuelle HP auf 0 setzen
        hp = 0

        // HP auf die ursprünglichen Standardwerte zurücksetzen
        hp = standartHp

        // Aktuelle Angriffskraft (ATK) auf 0 setzen
        atk = 0

        // ATK auf die ursprünglichen Standardwerte zurücksetzen
        atk = standartAtk

        // Aktuelle Verteidigungskraft (DEF) auf 0 setzen
        def = 0

        // DEF auf die ursprünglichen Standardwerte zurücksetzen
        def = standartDef

        // Aktuelles Level (LVL) auf 0 setzen
        lvl = 0

        // LVL auf die ursprünglichen Standardwerte zurücksetzen
        lvl = standartLvl
    }

    // Zukunfsfunktion
    fun heilen() {

    }
}
/*
// wird vielleicht eingebaut
fun pokemonEntwicklung() {


    if ((canEvolve && lvl == evolveLvl)) {
        when(name){
            "Glumanda"-> {
                trainerPokemon.replaceAll { trainerPokemon.containsAll(name =="Glumanda" }()
                trainerPokemon.add(Pokemon(
                "Glutexo",
                PokemonType.FEUER,
                1,
                58,
                64,
                58,
                mutableListOf(PokemonAttacke.Kratzer, PokemonAttacke.Glut, PokemonAttacke.Flammenwurf)
            ))

            }
        }

    } else

}

}
/*var isProtected = false

fun schutzZauber(ziel: Pokemon){
ziel.isProtected = true
}
enum Attacke (…){


fun effect(ziel: Pokemon){

    when(this)
    feuerBall -> ziel.hp - 50
    donner -> ziel.hp - 100; ziel.isParalysiert
    schutz -> ziel.isProtected = true
}

}
var starterPokemon(Schiggy(), Glumanda(), Bisasam())
canEvolve: Boolean
evolveLvl: Int

Gordon Lucas an Alle (27. Sept. 2023, 10:13)
fun evolve(){
if (canEvolve && currLvl == evolveLvl)
….
}
forEach (it.evolve)


when (name)

Glumanda -> name = Glutexo
fun evolve: Pokemon(){}
when (name)
Glumanda -> return Glutexo()

*/