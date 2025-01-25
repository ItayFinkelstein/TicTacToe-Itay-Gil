package fullstack.application.tic.tac.toe

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    enum class Turn(val symbol: Char) {
        NONE('-'),
        CROSS('X'),
        CIRCLE('O')
    }

    private val board = arrayOf(
        charArrayOf(Turn.NONE.symbol, Turn.NONE.symbol, Turn.NONE.symbol),
        charArrayOf(Turn.NONE.symbol, Turn.NONE.symbol, Turn.NONE.symbol),
        charArrayOf(Turn.NONE.symbol, Turn.NONE.symbol, Turn.NONE.symbol)
    )

    private var currentTurn = Turn.CROSS

    private val buttonIds = listOf(
        R.id.activity_main_topLeft_ImageButton,
        R.id.activity_main_topMiddle_ImageButton,
        R.id.activity_main_topRight_ImageButton,
        R.id.activity_main_middleLeft_ImageButton,
        R.id.activity_main_center_ImageButton,
        R.id.activity_main_middleRight_ImageButton,
        R.id.activity_main_bottomLeft_ImageButton,
        R.id.activity_main_bottomMiddle_ImageButton,
        R.id.activity_main_bottomRight_ImageButton
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main_constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBoard()

        val playAgainButton: Button = findViewById(R.id.activity_main_playAgain_button)
        playAgainButton.setOnClickListener { resetGame() }
    }

    private fun setupBoard() {
        buttonIds.forEachIndexed { index, id ->
            val row = index / 3
            val col = index % 3
            listenToBlockPress(row, col, id)
        }
    }

    private fun listenToBlockPress(row: Int, column: Int, idToListenTo: Int) {
        findViewById<ImageButton>(idToListenTo).setOnClickListener {
            if (board[row][column] == Turn.NONE.symbol) {
                board[row][column] = currentTurn.symbol
                val imageButton : ImageButton = findViewById(idToListenTo)
                if (currentTurn == Turn.CROSS) {
                    imageButton.setImageResource(R.drawable.x)
                } else if (currentTurn == Turn.CIRCLE) {
                    imageButton.setImageResource(R.drawable.o)
                }

                val turnTextView: TextView = findViewById(R.id.activity_main_turn_result_display_textView)

                val winner = getWinner()
                if (winner != "No winner") {
                    turnTextView.setText(winner)

                    val playAgainButton: Button = findViewById(R.id.activity_main_playAgain_button)
                    playAgainButton.visibility = Button.VISIBLE
                    disableAllImageButtons()

                    if (winner == "Draw") {
                        turnTextView.setBackgroundColor(resources.getColor(R.color.grey, theme))
                    }
                } else {
                    switchTurn()
                }

            }
        }
    }

    private fun disableAllImageButtons() {
        buttonIds.forEach { id ->
            findViewById<ImageButton>(id).isEnabled = false
        }
    }

    private fun resetGame() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] = Turn.NONE.symbol
            }
        }

        buttonIds.forEach { id ->
            findViewById<ImageButton>(id).apply {
                setImageResource(0)
                isEnabled = true
            }
        }

        currentTurn = Turn.CROSS
        val turnTextView: TextView = findViewById(R.id.activity_main_turn_result_display_textView)
        turnTextView.setBackgroundColor(resources.getColor(R.color.red, theme))
        turnTextView.setText("Turn ${currentTurn.symbol}")

        val playAgainButton: Button = findViewById(R.id.activity_main_playAgain_button)
        playAgainButton.visibility = Button.INVISIBLE
    }

    private fun switchTurn() {
        val turnTextView: TextView = findViewById(R.id.activity_main_turn_result_display_textView)

        if (currentTurn == Turn.CROSS) {
            currentTurn = Turn.CIRCLE
            turnTextView.setBackgroundColor(resources.getColor(R.color.blue, theme))
        } else {
            currentTurn = Turn.CROSS
            turnTextView.setBackgroundColor(resources.getColor(R.color.red, theme))
        }

        turnTextView.setText("Turn ${currentTurn.symbol}")
    }

    private fun getWinner(): String {
        // Check rows and columns
        for (i in board.indices) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Turn.NONE.symbol) {
                return "Winner: ${board[i][0]}"
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Turn.NONE.symbol) {
                return "Winner: ${board[0][i]}"
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Turn.NONE.symbol) {
            return "Winner: ${board[0][0]}"
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Turn.NONE.symbol) {
            return "Winner: ${board[0][2]}"
        }

        // Check for draw
        if (board.all { row -> row.all { cell -> cell != Turn.NONE.symbol } }) {
            return "Draw"
        }

        // No winner
        return "No winner"
    }
}