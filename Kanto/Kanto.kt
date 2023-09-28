package Kanto

import Attacken.PokemonAttacke
import Items.Heiler
import Items.Pokeball
import Pokemon.Pokemon
import Pokemon.pokemonList
import PokemonTypen.PokemonType
import Trainer.Boss


// Starterpokemon
private var glumanda =
    Pokemon(
        "Glumanda",
        PokemonType.FEUER,
        95,
        200,
        1000,
        100,
        mutableListOf(PokemonAttacke.Kratzer, PokemonAttacke.Glut)
    )
private var shiggy = Pokemon(
    "Schiggy", PokemonType.WASSER, 5, 44, 48, 65, mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Aquaknarre)
)
private var bisasam =
    Pokemon("Bisasam", PokemonType.PFLANZE, 5, 45, 49, 49, mutableListOf(PokemonAttacke.Tackle, PokemonAttacke.Absorb))


// Liste Für Pokemon für den Trainer
var trainerPokemon: MutableList<Pokemon> = mutableListOf()

// Die Klasse ist die Welt
open class Kanto() {

    // Checkt ob alle Pokemon HP 0 sind
    val continueGame: Boolean
        get() {
            return trainerPokemon.any { it.hp > 0 }
        }

    // Boss inizialiesiert
    var boss: Boss = Boss()

    // Gemschte Kopie der Pokemon Liste
    var pokemonListe: MutableList<Pokemon> = pokemonList.shuffled().toMutableList()

    /**
     * Diese Funktion ermöglicht dem Spieler die Auswahl eines Starter-Pokémon aus einer vordefinierten Liste.
     */
    fun starterPokemonAuswahl() {

        // Ankündigung der verfügbaren Starter-Pokémon
        printLetterByLetter("Diese Pokemon habe ich für dich zu auswahl:\n\n")
        var validInput = false

        // Liste der verfügbaren Starter-Pokémon
        val starterPokemon = mutableListOf<Pokemon>(glumanda, shiggy, bisasam)

        // Zeigt die verfügbaren Pokémon mit Verzögerung an
        for (pokemon in starterPokemon) {
            println(pokemon)
            Thread.sleep(1000)
        }
        while (!validInput) {
            try {
                // Spieler wählt ein Pokémon aus
                printLetterByLetter("1️⃣ für : ${glumanda.name} 2️⃣ für : ${shiggy.name} 3️⃣ für : ${bisasam.name}")
                println()
                Thread.sleep(500)


                println("Wähle ein Pokemon aus gebe eine Zahl von 1️⃣ bis 3️⃣ ein: ")

                val input = readln().toInt()

                // Hinzufügen des ausgewählten Pokémon zum Team des Spielers
                when (input) {
                    1 -> trainerPokemon.add(starterPokemon[input - 1])
                    2 -> trainerPokemon.add(starterPokemon[input - 1])
                    3 -> trainerPokemon.add(starterPokemon[input - 1])

                }

                // Wiedergabe des Geräuschs des ausgewählten Pokémon
                starterPokemon[input - 1].pokemonGeraeusche()
                printLetterByLetter("👍 Du hast dich für ${starterPokemon[input - 1].name} entschieden gute Wahl 👍\n\n")
                return

            } catch (e: Exception) {
                println("❌ Falsche Eingabe Versuche es nochmal ❌")
                validInput = false
            }
        }
    }

    /**
     * Diese Funktion startet einen wilden Pokémon-Kampf zwischen dem Spieler und einem wilden Pokémon.
     *
     * @param trainerPokemon Die Pokémon des Spielers in Form einer Liste.
     */
    fun kampfWildePokemon() {

        // Zufällige Auswahl eines wilden Pokémon aus der Pokémon-Liste
        var wildePokemon = pokemonListe.random()

        // Auswahl des ersten Pokémon des Spielers
        var playerPokemon = trainerPokemon.first()

        // Ankündigung des Kampfes
        printLetterByLetter("\nEin wildes ${wildePokemon.name}, Level: ${wildePokemon.lvl} ist aufgetaucht\n")
        printLetterByLetter("‼️‼️‼️‼️‼️‼️‼️‼️‼️‼️\n")
        printLetterByLetter("Ich Wähle Dich ${playerPokemon.name} ‼️\n\n")
        playerPokemon.pokemonGeraeusche()
        printLetterByLetter("🥊 Kampf zwischen ${playerPokemon.name} und Wildem ${wildePokemon.name} 🥊\n\n")
        printLetterByLetter("‼️‼️‼️‼️‼️‼️‼️‼️‼️‼️\n")

        // Der Kampf läuft, solange das wilde Pokémon und mindestens ein Pokémon des Spielers noch HP übrig haben
        while (wildePokemon.hp > 0 && trainerPokemon.any { it.hp > 0 }) {


            // Anzeige der aktuellen HP und Level beider Pokémon
            println()
            printLetterByLetter("${wildePokemon.name}  HP: ${wildePokemon.hp}  LVL:${wildePokemon.lvl}\n\n")

            printLetterByLetter("${playerPokemon.name} HP: ${playerPokemon.hp} LVL:${playerPokemon.lvl}\n\n")

            // Überprüfung der Spieler-Eingabe
            var validInput = false


            while (!validInput) {
                try {
                    println("1️⃣: Attacke 2️⃣: Fliehen\n3️⃣: Items 4️⃣: Deine Pokemon:")
                    var input = readln().toInt()
                    when (input) {
                        1 -> {
                            // Auswahl einer Attacke
                            printLetterByLetter("💥Wähle eine Attacke aus💥:\n")
                            playerPokemon.attacke.forEachIndexed { i, attack ->
                                println("${i + 1}: $attack")
                            }
                            var input = readln().toInt()
                            if (input <= playerPokemon.attacke.size) {
                                attackeAuswahl(playerPokemon, wildePokemon, input - 1)
                                validInput = true
                            } else {
                                println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌")
                                validInput = false
                            }

                        }

                        2 -> {
                            // Flucht aus dem Kampf
                            printLetterByLetter("Du bist geflohen‼️🏃‍💨\n\n")
                            printLetterByLetter("🌍 Läuft Durch die Welt 🌎\n\n")
                            return
                        }

                        3 -> {
                            // Auswahl eines Items
                            printLetterByLetter("1️⃣: Heiler 2️⃣: Pokéball\n🗃️Wähle ein Item aus der Item Box🗃️:\n")
                            var input = readln().toInt()
                            when (input) {
                                1 -> {
                                    // Heilung des Spieler-Pokémon
                                    Heiler().heilen(playerPokemon)
                                    println()
                                    printLetterByLetter("🩹🩹${playerPokemon.name} wurde um ${playerPokemon.standartHp - playerPokemon.hp} geheilt🩹🩹")
                                }

                                2 -> {
                                    // Versuch, das wilde Pokémon zu fangen
                                    val fangchance = (10..100).random()
                                    Pokeball().pokemonFangen(wildePokemon, fangchance, trainerPokemon)
                                    return

                                }

                                else -> {
                                    println()
                                    println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌\n")
                                    continue
                                }
                            }
                            validInput = true
                        }

                        4 -> {
                            // Auswahl eines anderen Pokémon aus dem Team des Spielers
                            playerPokemon = pokemonAuswahl()
                            validInput = true
                        }

                        else -> {
                            println()
                            println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌\n")
                            validInput = false
                        }
                    }
                } catch (e: Exception) {
                    println()
                    println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌\n")
                    validInput = false
                }
            }
            // Das wilde Pokémon führt einen Angriff aus, falls es noch lebt
            if (wildePokemon.hp > 0) {
                wildePokemonAttacke(wildePokemon, playerPokemon)
            }
        }
        // Das Spieler-Pokémon hat keine HP mehr, es wird ausgetauscht
        if (playerPokemon.hp < 0) {
            printLetterByLetter("🪦 ${playerPokemon.name} wurde besiegt ‼️🪦\n\n")
            playerPokemon = pokemonAuswahl()
        }
        // Der Kampf ist vorbei, das wilde Pokémon wurde besiegt
        if (wildePokemon.hp < 0) {
            println()
            printLetterByLetter("🪦 Wildes ${wildePokemon.name} wurde besiegt ‼️🪦\n\n")
            Items.printLetterByLetter("❌ Kampf beendet ❌\n")
            Items.printLetterByLetter("🌍Läuft Durch die Welt🌎\n")
            playerPokemon.standartHp
            playerPokemon.lvlUp()
            pokemonListe
            return
        }
    }


    /**
     * Diese Funktion berechnet den Schaden, den ein angreifendes Pokémon einem verteidigenden Pokémon mit einer bestimmten Attacke zufügt.
     *
     * @param pokemonAtk Das angreifende Pokémon.
     * @param pokeonDef Das verteidigende Pokémon.
     * @param attackName Die verwendete Attacke.
     * @return Der berechnete Schaden als Ganzzahl.
     */
    fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {

        // Der Basis-Schaden der Attacke
        val basisSchaden = attackName.schaden

        // Die Schadensberechnung unter Berücksichtigung der Angriffs- und Verteidigungswerte der Pokémon
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def
        return schaden

    }

    /**
     * Diese Funktion simuliert die Bewegung des Spielers in der Spielwelt.
     * Der Spieler bewegt sich, sammelt Schritte und hat die Chance, auf wilde Pokémon zu treffen oder gegen seinen Rivalen anzutreten.
     */
    fun bewegung() {

        // Anzeige der Bewegung in der Welt
        printLetterByLetter("🌍Läuft Durch die Welt🌎\n")

        // Initialisierung der Schritte und zufällige Schritte bis zur Begegnung mit wilden Pokémon
        var steps = 0
        val stepsUntilWildPokemonAppears = (10..50).random()

        // Schleife für die Bewegung
        do {
            printLetterByLetter("💨👟‍")
            steps += (10..20).random()
            // Überprüfung, ob eine Begegnung mit wilden Pokémon stattfindet
            if (steps % stepsUntilWildPokemonAppears == 0) {
                kampfWildePokemon()
            }
            // Überprüfung, ob der Spieler bereit ist, gegen seinen Rivalen anzutreten
            else if (trainerPokemon.any { it.lvl >= 100 }) {
                printLetterByLetter("\n🥊Du Bist bereit gegen dein Rivalen zu Kämpfen🥊\n\n")
                return

            }
        } while (continueGame)
    }

    /**
     * Diese Funktion ermöglicht dem Spieler die Auswahl eines Pokémon aus seinem aktuellen Team.
     *
     * @return Das ausgewählte Pokémon.
     */
    fun pokemonAuswahl(): Pokemon {
        // Anzeige der Auswahlmöglichkeiten
        println("Wähle ein Pokemon aus:")

        // Anzeige der Pokémon im Trainer-Team
        trainerPokemon.forEachIndexed { index, pokemon ->
            println("${index + 1}. ${pokemon.name}")
        }
        try {
            // Spieler wählt ein Pokémon aus
            val index = readln().toInt() - 1

            // Überprüfung der Auswahl
            if (index >= 0 && index < trainerPokemon.size) {

                val selectedPokemon = trainerPokemon[index]
                println()
                printLetterByLetter("${selectedPokemon.name}, Ich Wähle Dich ‼️")
                selectedPokemon.pokemonGeraeusche()
                return selectedPokemon
            } else {
                println()
                printLetterByLetter("❌ Ungültige Auswahl ‼️ Bitte eine Zahl ein von 1 bis ${trainerPokemon.size} ❌")
            }
        } catch (e: Exception) {
            println()
            printLetterByLetter("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌")
        }
        // Rekursiver Aufruf, falls die Auswahl ungültig war
        return pokemonAuswahl()
    }


    /**
     * Diese Funktion ermöglicht dem Spieler die Auswahl einer Attacke für sein Pokémon und führt die Attacke gegen ein wildes Pokémon aus.
     *
     * @param playerPokemon Das Pokémon des Spielers, das die Attacke ausführt.
     * @param wildePokemon Das wilde Pokémon, gegen das die Attacke ausgeführt wird.
     * @param attackeIndex Der Index der ausgewählten Attacke im Attacke-Array des Spieler-Pokémons.
     */
    fun attackeAuswahl(playerPokemon: Pokemon, wildePokemon: Pokemon, attackeIndex: Int) {

        // Auswahl der auszuführenden Attacke
        var playerAttack = playerPokemon.attacke[attackeIndex]

        // Berechnung des Schadens, den die Attacke verursacht
        val damage = schaden(playerPokemon, wildePokemon, playerAttack)

        // Anzeige des Angriffs und des verursachten Schadens
        println()
        printLetterByLetter("💥${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu ‼️💥\n")

        // Reduzierung der HP des wilden Pokémon entsprechend dem verursachten Schaden
        wildePokemon.hp -= damage

        // Anzeige der verbleibenden HP des wilden Pokémon
        printLetterByLetter("Wildes ${wildePokemon.name} hat noch ${wildePokemon.hp} HP\n")
        return
    }

    /**
     * Diese Funktion simuliert den Angriff eines wilden Pokémon auf das Pokémon des Spielers und berechnet den verursachten Schaden.
     *
     * @param wildePokemon Das wilde Pokémon, das den Angriff ausführt.
     * @param playerPokemon Das Pokémon des Spielers, das verteidigt.
     */
    fun wildePokemonAttacke(wildePokemon: Pokemon, playerPokemon: Pokemon) {

        // Zufällige Auswahl einer Attacke für das wilde Pokémon
        val wildAttack = wildePokemon.attacke.random()

        // Berechnung des Schadens, den die Attacke verursacht
        val damage = schaden(wildePokemon, playerPokemon, wildAttack)

        // Anzeige des Angriffs und des verursachten Schadens
        println()
        printLetterByLetter(" 💥Wildes ${wildePokemon.name} setzt $wildAttack ein und fügt $damage Schaden zu ‼️ 💥\n")

        // Reduzierung der HP (Lebenspunkte) des Spieler-Pokémons entsprechend dem verursachten Schaden
        playerPokemon.hp -= damage

        // Anzeige der verbleibenden HP des Spieler-Pokémons
        printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp}HP\n")
        return
    }

    /**
     * Diese Funktion repräsentiert das Hauptspiel, bei dem der Spieler sein Starter-Pokémon auswählt, die Spielwelt erkundet
     * und gegen den Boss kämpft. Das Spiel wird wiederholt gespielt, solange der Spieler sich entscheidet, ein neues Spiel zu starten.
     */
    fun game() {
        do {

            // Der Spieler wählt sein Starter-Pokémon aus
            starterPokemonAuswahl()

            // Der Spieler bewegt sich in der Spielwelt und hat die Chance auf Begegnungen und Kämpfe
            bewegung()

            // Der Spieler tritt gegen den Boss an
            boss.bossKampf()

        } while (newGame())


    }

    /**
     * Diese Funktion fragt den Spieler, ob er ein neues Spiel starten möchte, und verarbeitet seine Entscheidung.
     *
     * @return `true`, wenn der Spieler ein neues Spiel starten möchte, andernfalls `false`.
     */
    fun newGame(): Boolean {

        // Kopie des Trainer-Pokémon-Teams
        var playerPokemon = trainerPokemon

        // Abfrage, ob der Spieler ein neues Spiel starten möchte
        println("Willst du nochmal Zocken? (ja/nein): ")
        val entscheidung = readln().trim().lowercase()
        Thread.sleep(1000)

        // Zurücksetzen von Werten und Listen im Spiel, wenn der Spieler zustimmt
        when (entscheidung) {
            "ja", "j", "yes", "y" -> {
                println("Noch eine Runde 👍")
                // Zurücksetzen der Starter-Pokémon und des Trainer-Teams
                glumanda.reset()
                shiggy.reset()
                bisasam.reset()
                playerPokemon.clear()
                return true
            }
            // Beendet das Spiel
            "nein", "n", "no" -> {
                printLetterByLetter("Danke fürs Zocken‼️")
                printLetterByLetter("Bye 👋👋👋")
                return false
            }
            // Bei falscher Eingabe wiederholen
            else -> {
                println("❌ Ungültige Eingabe ‼️ Bitte ja oder nein eingeben❌")
                return newGame()
            }
        }
    }

    /**
     * Diese Funktion gibt einen Text Buchstabe für Buchstabe aus und simuliert dadurch einen typischen Texteffekt.
     *
     * @param text Der Text, der Buchstabe für Buchstabe ausgegeben werden soll.
     */
    fun printLetterByLetter(text: String) {
        // Schleife, um jeden Buchstaben im Text nacheinander auszugeben
        for (char in text) {
            print(char)
            Thread.sleep(0) // Eine kurze Verzögerung für den Effekt
        }
    }
}