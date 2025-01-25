package fullstack.application.tic.tac.toe

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val board = arrayOf(
        charArrayOf('-', '-', '-'),
        charArrayOf('-', '-', '-'),
        charArrayOf('-', '-', '-')
    )

    private var turn = 'X';

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main_constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listenToBlockPress(0, 0, R.id.activity_main_topLeft_ImageButton)
        listenToBlockPress(0, 1, R.id.activity_main_topMiddle_ImageButton)
        listenToBlockPress(0, 2, R.id.activity_main_topRight_ImageButton)
        listenToBlockPress(1, 0, R.id.activity_main_middleLeft_ImageButton)
        listenToBlockPress(1, 1, R.id.activity_main_center_ImageButton)
        listenToBlockPress(1, 2, R.id.activity_main_middleRight_ImageButton)
        listenToBlockPress(2, 0, R.id.activity_main_bottomLeft_ImageButton)
        listenToBlockPress(2, 1, R.id.activity_main_bottomMiddle_ImageButton)
        listenToBlockPress(2, 2, R.id.activity_main_bottomRight_ImageButton)
    }

    private fun listenToBlockPress(row: Int, column: Int, idToListenTo: Int) {
        findViewById<ImageButton>(idToListenTo).setOnClickListener {
            board[row][column] = turn;
            val imageButton = findViewById<ImageButton>(idToListenTo);
            if (turn == 'X') {
                imageButton.setImageResource(R.drawable.x)  // Replace with your X PNG resource
            } else if (turn == 'O') {
                imageButton.setImageResource(R.drawable.o)  // Replace with your O PNG resource
            }
            afterTurn()
        }
    }

    private fun afterTurn() {
        turn = if (turn == 'X') 'O' else 'X'
    }

}