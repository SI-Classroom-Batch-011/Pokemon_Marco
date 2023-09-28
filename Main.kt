import Kanto.Kanto
var millis0 : Long = 0
var millis5 : Long = 5
var millis10 : Long = 10
var millis50 : Long = 50
var millis100 : Long = 100

/**
 * Diese Funktion heißt den Spieler in der Pokémon-Welt von Kanto willkommen und fordert seinen Namen an, um die Reise zu beginnen.
 *
 * Die Funktion gibt eine grafische Begrüßungsnachricht aus und fordert den Spieler auf, seinen Namen einzugeben.
 * Nachdem der Spieler seinen Namen eingegeben hat, wird eine Bestätigungsnachricht angezeigt.
 */
fun wilkommen(){

    // Ausgabe einer grafischen Begrüßungsnachricht
    printLetterByLetter(
        """
                                              ,'\
    _.----.        ____         ,'  _\   ___    ___     ____
_,-'       `.     |    |  /`.   \,-'    |   \  /   |   |    \  |`.
\      __    \    '-.  | /   `.  ___    |    \/    |   '-.   \ |  |
 \.    \ \   |  __  |  |/    ,','_  `.  |          | __  |    \|  |
   \    \/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
    \     ,-'/  /   \    ,'   | \/ / ,`.|         /  /   \  |     |
     \    \ |   \_/  |   `-.  \    `'  /|  |    ||   \_/  | |\    |
      \    \ \      /       `-.`.___,-' |  |\  /| \      /  | |   |
       \    \ `.__,'|  |`-._    `|      |__| \/ |  `.__,'|  | |   |
        \_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                `'                            '-._|
        """.trimIndent()
    ,millis5)
    println()
    Thread.sleep(100)
    println()
    Thread.sleep(100)
    println()
    Thread.sleep(100)
    println()


    printLetterByLetter("🌍🌎Willkommen in der Pokemon Welt von Kanto🌍🌎\n",millis5)
    printLetterByLetter("Wie ist dein Name ?\n",millis5)

    // Eingabe des Spielernamens
    var inputName = readln()

    // Bestätigungsnachricht für den eingegebenen Namen
    printLetterByLetter("$inputName hört sich nach einem Namen an für einen Pokemon Meister\n",millis5)
    printLetterByLetter("Um deine Reise zu beginnen brauchst du zuerst ein Pokemon\n",millis5)
    return
}


fun main(){
   var kantoo = Kanto()
    wilkommen()
    kantoo.game()
}