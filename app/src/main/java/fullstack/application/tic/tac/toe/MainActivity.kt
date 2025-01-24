package fullstack.application.tic.tac.toe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main_constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val matrix = arrayOf(
            charArrayOf('-', '-', '-'),
            charArrayOf('-', '-', '-'),
            charArrayOf('-', '-', '-')
        )

        var turn = 'X';

        findViewById<ImageButton>(R.id.activity_main_topLeft_ImageButton).setOnClickListener {
            matrix[0][0] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_topMiddle_ImageButton).setOnClickListener {
            matrix[0][1] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_topRight_ImageButton).setOnClickListener {
            matrix[0][2] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_middleLeft_ImageButton).setOnClickListener {
            matrix[1][0] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_center_ImageButton).setOnClickListener {
            matrix[1][1] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_middleRight_ImageButton).setOnClickListener {
            matrix[1][2] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_bottomLeft_ImageButton).setOnClickListener {
            matrix[2][0] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_bottomMiddle_ImageButton).setOnClickListener {
            matrix[2][1] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
        findViewById<ImageButton>(R.id.activity_main_bottomRight_ImageButton).setOnClickListener {
            matrix[2][2] = turn;
            turn = if (turn == 'X') 'O' else 'X'
        }
    }
}