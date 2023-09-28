package Trainer

import Attacken.PokemonAttacke
import Items.Heiler
import Kanto.Kanto
import Kanto.trainerPokemon
import Pokemon.Pokemon
import PokemonTypen.PokemonType

// Klasse für den Boss
class Boss(
    var name: String = "Rivale",
    var bossPokemon: MutableList<Pokemon> = mutableListOf(
        Pokemon(
            "Mewtu",
            PokemonType.PSYCHO,
            1,
            106,
            110,
            90,
            mutableListOf(
                PokemonAttacke.Konfusion,
                PokemonAttacke.Psychokinese,
                PokemonAttacke.Psychoschock,
                PokemonAttacke.Barriere
            )
        ), Pokemon(
            "Dragoran",
            PokemonType.DRACHE,
            1,
            91,
            134,
            95,
            mutableListOf(
                PokemonAttacke.Donnerwelle,
                PokemonAttacke.Ruckzuckhieb,
                PokemonAttacke.Donner,
                PokemonAttacke.Wutanfall
            )
        ), Pokemon(
            "Arktos",
            PokemonType.EIS,
            50,
            90,
            85,
            100,
            mutableListOf(PokemonAttacke.Eisstrahl, PokemonAttacke.Blizzard, PokemonAttacke.Flügelschlag)
        ), Pokemon(
            "Lavados",
            PokemonType.FEUER,
            50,
            90,
            100,
            90,
            mutableListOf(
                PokemonAttacke.Glut,
                PokemonAttacke.Feuerwirbel,
                PokemonAttacke.Flammenwurf,
                PokemonAttacke.Flügelschlag
            )
        )
    )
) {

    /**
     * Diese Funktion startet den Kampf gegen den Boss in der Pokémon-Welt von Kanto.
     *
     * Der Spieler kämpft gegen den Boss, indem er seine Pokémon einsetzt und verschiedene Aktionen wählt, darunter Angriffe,
     * die Verwendung von Items und das Wechseln von Pokémon.
     */
    fun bossKampf() {

        // Kopie der Spieler-Pokémon erstellen
        var copyPlayerPokemon = trainerPokemon

        // Aktuelles Spieler-Pokémon auswählen
        var playerPokemon = copyPlayerPokemon.first()

        // Zufälliges Boss-Pokémon auswählen
        var bossPokemons = bossPokemon.random()

        // Kampfbeginn anzeigen
        printLetterByLetter("🥊 Kampf zwischen ${this.name} und Spieler 🥊\n\n")
        printLetterByLetter("Du Bist Dran ${playerPokemon.name}\n")
        playerPokemon.pokemonGeraeusche()
        printLetterByLetter("${playerPokemon.name}, HP: ${playerPokemon.hp}, Level: ${playerPokemon.lvl}\n")
        printLetterByLetter("Rivale setzt ${bossPokemons.name},HP ${bossPokemons.hp}, Level: ${bossPokemons.lvl} ein\n")
        bossPokemons.pokemonGeraeusche()
        printLetterByLetter("🥊 Kampf zwischen ${playerPokemon.name} und ${bossPokemons.name} ‼️ 🥊\n")

        // Kampfschleife: Der Kampf dauert solange, wie sowohl das Boss-Pokémon als auch die Spieler-Pokémon noch HP haben
        while (bossPokemon.any { it.hp > 0 } && copyPlayerPokemon.any { it.hp > 0 }) {

            println()
            printLetterByLetter("${bossPokemons.name}\nHP: ${bossPokemons.hp}, LVL: ${bossPokemons.lvl}\n\n")

            printLetterByLetter("${playerPokemon.name}\nHP: ${playerPokemon.hp},LVL: ${playerPokemon.lvl}\n\n")

            var validInput = false

            // Eingabeaufforderung für den Spieler
            while (!validInput) {
                try {
                    println("1️⃣: Attacke 2️⃣: Fliehen\n3️⃣: Items 4️⃣: Pokemon:")
                    var input = readln().toInt()
                    when (input) {
                        1 -> {
                            // Auswahl einer Attacke durch den Spieler
                            printLetterByLetter("💥 Whähle eine Attacke aus 💥:\n")
                            playerPokemon.attacke.forEachIndexed { i, attack ->
                                println("${i + 1}: $attack")
                            }
                            var input = readln().toInt()
                            if (input <= playerPokemon.attacke.size) {
                                // Ausführung des Angriffs
                                attackeAuswahl(playerPokemon, bossPokemons, input - 1)
                                validInput = true
                            } else {
                                println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌")
                                validInput = false
                            }

                        }

                        2 -> {
                            // Flucht ist im Bosskampf nicht erlaubt
                            printLetterByLetter("❌ Du Kannst nicht Fliehen ❌")
                            validInput = false
                        }

                        3 -> {
                            // Auswahl von Items durch den Spieler
                            printLetterByLetter("1️⃣: Heiler 2️⃣: Pokéball\n🗃️Wähle ein Item aus der Item Box🗃️:")
                            println()
                            var input = readln().toInt()
                            when (input) {
                                1 -> {
                                    // Heilung des Spieler-Pokémon
                                    Heiler().heilen(playerPokemon)
                                    println()
                                    printLetterByLetter("🩹🩹 ${playerPokemon.name} wurde um ${playerPokemon.hp - playerPokemon.standartHp} geheilt 🩹🩹")
                                }

                                2 -> {
                                    // Der Spieler kann keine Trainer-Pokémon fangen
                                    printLetterByLetter("❌ Du kannst keine Trainer Pokemon fangen ❌\n")
                                    continue
                                }

                                else -> {
                                    println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌")
                                    continue
                                }
                            }
                            validInput = true
                        }

                        4 -> {
                            // Auswahl eines anderen Pokémon durch den Spieler
                            playerPokemon = pokemonAuswahl()
                            validInput = true
                        }

                        else -> {
                            println()
                            println("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌")

                            validInput = false
                        }
                    }
                } catch (e: Exception) {
                    println()
                    println("❌Ungültige Eingabe ‼️ Bitte eine Zahl eingeben❌")
                    validInput = false
                }
            }

            // Wenn das Boss-Pokémon noch HP hat, führt es seinen Angriff aus
            if (bossPokemons.hp > 0) {
                bossAttack(bossPokemons, playerPokemon)
            }

            // Wenn das Spieler-Pokémon keine HP mehr hat, wird es entfernt
            if (playerPokemon.hp < 0) {
                printLetterByLetter("💀${playerPokemon.name} wurde besiegt‼️🪦\n")
                copyPlayerPokemon.remove(playerPokemon)
                // Überprüfen, ob alle Spieler-Pokémon besiegt wurden
                if (copyPlayerPokemon.isEmpty()) {
                    printLetterByLetter("💀GAME OVER💀\n")
                    return
                } else
                    playerPokemon = pokemonAuswahl()
            }
            // Prüfen, ob das Boss-Pokémon besiegt wurde
            if (bossPokemons.hp < 0) {
                println()
                printLetterByLetter("💀${bossPokemons.name} wurde besiegt‼️🪦\n")
                bossPokemon.remove(bossPokemons)
                if (bossPokemon.isEmpty()) {
                    printLetterByLetter("DAS KANN NICHT SEIN 😱😭\n")
                    printLetterByLetter("🏆🏆Du hast das Spiel durchgespielt🏆🏆\n")
                    printLetterByLetter("🎉🎉 DU BIST POKEMONMEISTER 🎉🎉\n")
                    return
                } else {
                    bossPokemon.shuffle()
                    bossPokemons = bossPokemon[0]
                    printLetterByLetter("ICH BIN NOCH NICHT GESCHLAGEN ‼️ 😉\n")
                    printLetterByLetter("Du Bist Dran ${bossPokemons.name}\n")
                    bossPokemons.pokemonGeraeusche()
                    continue

                }
            }

        }


        return
    }

    /**
     * Diese Funktion berechnet den Schaden, den ein Pokémon mit einer bestimmten Attacke einem anderen Pokémon zufügt.
     *
     * @param pokemonAtk Das angreifende Pokémon.
     * @param pokeonDef Das verteidigende Pokémon.
     * @param attackName Die ausgewählte Attacke, für die der Schaden berechnet wird.
     * @return Der berechnete Schaden als ganze Zahl.
     */
    fun schaden(pokemonAtk: Pokemon, pokeonDef: Pokemon, attackName: PokemonAttacke): Int {
        // Basis-Schaden aus der ausgewählten Attacke abrufen
        val basisSchaden = attackName.schaden

        // Schadenberechnung unter Berücksichtigung der Angriffs- und Verteidigungswerte
        val schaden = (pokemonAtk.atk * basisSchaden) / pokeonDef.def

        // Rückgabe des berechneten Schadens
        return schaden

    }

    /**
     * Diese Funktion ermöglicht dem Spieler die Auswahl eines Pokémon aus seinem Team.
     *
     * @return Das ausgewählte Pokémon aus dem Spieler-Team.
     */
    fun pokemonAuswahl(): Pokemon {
        // Zeige dem Spieler die Liste der verfügbaren Pokémon zur Auswahl an.
        println("Wähle ein Pokémon aus:")
        trainerPokemon.forEachIndexed { index, pokemon ->
            println("${index + 1}. ${pokemon.name}")
        }

        try {
            // Der Spieler gibt die Nummer des ausgewählten Pokémon ein.
            val index = readln().toInt() - 1

            if (index >= 0 && index < trainerPokemon.size) {
                // Wenn die Auswahl gültig ist, wird das ausgewählte Pokémon zurückgegeben.
                val selectedPokemon = trainerPokemon[index]
                println()
                printLetterByLetter("${selectedPokemon.name} Ich Wähle Dich ‼️\n")
                selectedPokemon.pokemonGeraeusche()
                return selectedPokemon
            } else {
                // Wenn die Auswahl ungültig ist, wird der Spieler benachrichtigt und erneut aufgefordert.
                println()
                printLetterByLetter("❌ Ungültige Auswahl ‼️ Bitte eine Zahl zwischen 1 und ${trainerPokemon.size} eingeben ❌")
            }
        } catch (e: Exception) {
            // Falls eine ungültige Eingabe erfolgt, wird der Spieler benachrichtigt und erneut aufgefordert.
            println()
            printLetterByLetter("❌ Ungültige Eingabe ‼️ Bitte eine Zahl eingeben ❌\n")
        }
        // Die Funktion ruft sich selbst erneut auf, um sicherzustellen, dass der Spieler ein gültiges Pokémon auswählt.
        return pokemonAuswahl()
    }

    /**
     * Diese Funktion ermöglicht die Auswahl und Ausführung einer Attacke durch das Spieler-Pokémon im Kampf gegen den Boss.
     *
     * @param playerPokemon Das Spieler-Pokémon, das die Attacke ausführt.
     * @param bossPokemons Das Boss-Pokémon, gegen das die Attacke gerichtet ist.
     * @param attackeIndex Der Index der ausgewählten Attacke in der Angriffsliste des Spieler-Pokémons.
     */
    fun attackeAuswahl(playerPokemon: Pokemon, bossPokemons: Pokemon, attackeIndex: Int) {

        // Die ausgewählte Attacke des Spieler-Pokémons abrufen
        var playerAttack = playerPokemon.attacke[attackeIndex]

        // Schadenberechnung unter Verwendung der ausgewählten Attacke
        val damage = schaden(playerPokemon, bossPokemons, playerAttack)

        println()
        printLetterByLetter("💥${playerPokemon.name} setzt $playerAttack ein und fügt $damage Schaden zu ‼️💥\n")

        // Reduzieren der HP des Boss-Pokémons entsprechend dem verursachten Schaden
        bossPokemons.hp -= damage

        printLetterByLetter("${bossPokemons.name} hat noch ${bossPokemons.hp} HP\n")
    }

    /**
     * Diese Funktion ermöglicht dem Boss-Pokémon, eine zufällige Attacke gegen das Spieler-Pokémon auszuführen.
     *
     * @param bossPokemons Das Boss-Pokémon, das die Attacke ausführt.
     * @param playerPokemon Das Spieler-Pokémon, gegen das die Attacke gerichtet ist.
     */
    fun bossAttack(bossPokemons: Pokemon, playerPokemon: Pokemon) {

        // Zufällige Auswahl eines Angriffs des Boss-Pokémon aus dessen Liste von Attacken.
        val bossAttack = bossPokemons.attacke.random()

        // Berechnung des Schadens, den der Angriff verursacht.
        val damage = schaden(bossPokemons, playerPokemon, bossAttack)

        // Ausgabe des Angriffs und des verursachten Schadens.
        println()
        printLetterByLetter("💥${bossPokemons.name} setzt $bossAttack ein und fügt $damage Schaden zu‼️💥\n")

        // Reduzierung der HP des Spieler-Pokémon entsprechend dem verursachten Schaden.
        playerPokemon.hp -= damage

        println()

        // Ausgabe der verbleibenden HP des Spieler-Pokémon.
        printLetterByLetter("${playerPokemon.name} hat noch ${playerPokemon.hp} HP\n")
    }


    /**
     * Diese Funktion gibt einen Text Buchstabe für Buchstabe aus und simuliert dadurch einen typischen Texteffekt.
     *
     * @param text Der Text, der Buchstabe für Buchstabe ausgegeben werden soll.
     */
    fun printLetterByLetter(text: String) {
        for (char in text) {
            print(char)
            Thread.sleep(0)
        }
    }
}
