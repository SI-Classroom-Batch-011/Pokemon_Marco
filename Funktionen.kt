/**
 * Diese Funktion gibt einen Text Buchstabe für Buchstabe aus und simuliert dadurch einen typischen Texteffekt.
 *
 * @param text Der Text, der Buchstabe für Buchstabe ausgegeben werden soll.
 */
fun printLetterByLetter(text: String,millis: Long) {
    for (char in text) {
        print(char)
        Thread.sleep(millis)
    }
}

// Inspirations Funktion
fun indexEinlesen(min: Int, max: Int): Int {
    var index: Int

    do {
        try {
            println("Geben sie einen Index ein: (von $min bis $max)")
            index = readln().toInt()
        } catch (ex: Exception) {
            println("Sie haben einen Ungültigen Index eingegeben")
            println("Probieren sie es nochmal")
            index = -1000
            continue
        }
        //...

    } while (index !in min..max)

    return index
}