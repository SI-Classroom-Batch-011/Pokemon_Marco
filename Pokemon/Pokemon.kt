package Pokemon

import Attacken.PokemonAttacke


open class Pokemon(
    val name: String,
    val typ: PokemonType,
    var lvl: Int,
    var hp: Int,
    var atk: Int,
    var def: Int,
    var attacke : MutableList<PokemonAttacke>){

    var standartHp = hp
    var standartAtk = atk
    var standartDef = def
    var standartLvl = lvl
    fun pokemonGeraeusche (){
        println("$name,$name!!!")
    }
    override fun toString(): String {
       return "Name:${this.name}\n" +
               "Typ:${this.typ}\n" +
               "Level:${this.lvl}\n" +
               "Lebenspunkte:${this.hp}\n" +
               "Angriffskraft:${this.atk}\n" +
               "Verteidigungskraft:${this.def}\n" +
               "Attaken:${this.attacke}\n"
    }


    open fun lvlUp () {
        hp=0
        hp = standartHp
        standartHp+= 5
        atk += 2
        def += 2
        lvl++

    }
 open fun reset(){
     hp = 0
     hp = standartHp
     atk = 0
     atk = standartAtk
     def = 0
     def = standartDef
     lvl = 0
     lvl = standartLvl
 }
}
