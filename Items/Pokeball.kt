package Items


import Pokemon.Pokemon

// Klasse für den Pokeball
open class Pokeball : Items() {

    /**
     * Diese Funktion ermöglicht es dem Spieler, ein wildes Pokémon zu fangen, basierend auf einer Erfolgschance.
     *
     * @param wildPokemon Das wilde Pokémon, das gefangen werden soll.
     * @param erfolgschance Die Erfolgschance für das Fangen des wilden Pokémon (im Bereich von 0 bis 100).
     * @param trainerPokemon Die Liste der Pokémon des Spielers, in der das gefangene Pokémon hinzugefügt wird, falls erfolgreich.
     * @return True, wenn das Pokémon erfolgreich gefangen wurde, andernfalls False.
     */
    fun pokemonFangen(wildPokemon: Pokemon, erfolgschance: Int, trainerPokemon: MutableList<Pokemon>): Boolean {
        // Ausgabe, dass der Fangversuch gestartet wird.
        printLetterByLetter("🕸️ Du versuchst, das Wilde ${wildPokemon.name} zu fangen ‼️ 🕸️\n")

        if (erfolgschance >= 0 && erfolgschance <= 100) {
            // Zufällige Generierung einer Fangchance zwischen 0 und 100.
            val fangchance = (0..100).random()

            if (fangchance < erfolgschance) {
                if (trainerPokemon.size <= 4) {
                    // Erfolgreicher Fang: Ausgabe und Hinzufügen des gefangenen Pokémon zur Trainerliste.
                    println("👍 Erfolgreich ‼️ Du hast ${wildPokemon.name} gefangen 🕸 👍\n")
                    printLetterByLetter("❌ Kampf beendet ❌\n")
                    printLetterByLetter("🌍Läuft Durch die Welt 🌎\n")
                    trainerPokemon.add(wildPokemon)
                } else {
                    // Der Trainer hat bereits zu viele Pokémon.
                    println("❌ Du hast zu viele Pokémon ❌\n")
                }
                return true
            } else {
                // Der Fangversuch war nicht erfolgreich.
                printLetterByLetter("❌ Leider nicht erfolgreich ❌ ${wildPokemon.name} ist entkommen 💨💨\n")
                printLetterByLetter("❌ Kampf beendet ❌\n")
                printLetterByLetter("🌍 Läuft Durch die Welt 🌎\n")
            }
        }

        // Rückgabe, dass der Fangversuch nicht erfolgreich war.
        return false
    }
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

// Zukunftsfunktion
fun pokemonAustauschen() {

}