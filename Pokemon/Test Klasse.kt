import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class PokemonGame : JFrame(), ActionListener, KeyListener {
    private var playerX = 50 // x-Koordinate des Spielers
    private var playerY = 50 // y-Koordinate des Spielers

    init {
        title = "Pokemon Game"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(800, 600)
        isResizable = false

        addKeyListener(this)

        val timer = Timer(10, this)
        timer.start()
    }

    override fun paint(g: Graphics) {
        // Hier zeichnest du den Spieler und andere Spielobjekte
        val buffer = createImage(width, height)
        val g2d = buffer.graphics

        // Zeichne den Spieler
        g2d.color = Color.RED
        g2d.fillRect(playerX, playerY, 30, 30) // Hier wird ein einfaches rotes Rechteck für den Spieler verwendet

        g.drawImage(buffer, 0, 0, this)
    }

    override fun actionPerformed(e: ActionEvent) {
        // Hier wird das Spiel aktualisiert, z.B. Bewegung des Spielers
        repaint()
    }

    override fun keyTyped(e: KeyEvent) {}

    override fun keyPressed(e: KeyEvent) {
        // Hier reagierst du auf die Tasten, um die Bewegung des Spielers zu steuern
        when (e.keyCode) {
            KeyEvent.VK_UP -> playerY -= 5
            KeyEvent.VK_DOWN -> playerY += 5
            KeyEvent.VK_LEFT -> playerX -= 5
            KeyEvent.VK_RIGHT -> playerX += 5
        }
    }

    override fun keyReleased(e: KeyEvent) {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val game = PokemonGame()
                game.isVisible = true
            }
        }
    }
}