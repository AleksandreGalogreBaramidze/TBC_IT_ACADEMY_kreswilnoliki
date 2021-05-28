package com.example.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.test4.databinding.ActivityGame3DBinding

class Game3D : AppCompatActivity() {
    private lateinit var gameManager: GameFlow
    private lateinit var binding: ActivityGame3DBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGame3DBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init(){
        binding.one.background = null
        binding.two.background = null
        binding.three.background = null
        binding.four.background = null
        binding.five.background = null
        binding.six.background = null
        binding.seven.background = null
        binding.eight.background = null
        binding.nine.background = null
        gameManager = GameFlow()
        binding.one.setOnClickListener { onBoxClicked(binding.one,  ButtonPositions(0, 0)) }
        binding.two.setOnClickListener { onBoxClicked(binding.two,  ButtonPositions(0, 1)) }
        binding.three.setOnClickListener { onBoxClicked(binding.three,  ButtonPositions(0, 2)) }
        binding.four.setOnClickListener { onBoxClicked(binding.four,  ButtonPositions(1, 0)) }
        binding.five.setOnClickListener { onBoxClicked(binding.five,  ButtonPositions(1, 1)) }
        binding.six.setOnClickListener { onBoxClicked(binding.six,  ButtonPositions(1, 2)) }
        binding.seven.setOnClickListener { onBoxClicked(binding.seven,  ButtonPositions(2, 0)) }
        binding.eight.setOnClickListener { onBoxClicked(binding.eight,  ButtonPositions(2, 1)) }
        binding.nine.setOnClickListener { onBoxClicked(binding.nine, ButtonPositions(2, 2)) }

        binding.NewGame.setOnClickListener {
            gameManager.reset()
            restartButton()
        }
    }
    private fun updatePoints() {
        binding.player1Points.text = "Player 1 Points: ${gameManager.player1Points}"
        binding.player2Points.text = "Player 2 Points: ${gameManager.player2Points}"
    }
    private fun restartButton() {
        binding.one.background = null
        binding.two.background = null
        binding.three.background = null
        binding.four.background = null
        binding.five.background = null
        binding.six.background = null
        binding.seven.background = null
        binding.eight.background = null
        binding.nine.background = null
        binding.one.isEnabled = true
        binding.two.isEnabled = true
        binding.three.isEnabled = true
        binding.four.isEnabled = true
        binding.five.isEnabled = true
        binding.six.isEnabled = true
        binding.seven.isEnabled = true
        binding.eight.isEnabled = true
        binding.nine.isEnabled = true
    }
    private fun onBoxClicked(box: ImageButton, position: ButtonPositions) {
        if (box.background == null) {
            if(gameManager.currentPlayerMark == "X"){
                box.setBackgroundResource(R.drawable.ic_exposure_zero_black_24dp)
            }else{
                box.setBackgroundResource(R.drawable.ic_close_black_24dp)
            }
            val winningLine = gameManager.makeMove(position)
            if (winningLine != null) {
                updatePoints()
                disableBoxes()
                showWinner(winningLine)
            }else if(draw()){
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showWinner(winningLine: PlayerWin) {
        val (winningBoxes, background) = when (winningLine) {
            PlayerWin.ROW_1 -> Pair(listOf(binding.one, binding.two, binding.three), R.drawable.horizontal)
            PlayerWin.ROW_2 -> Pair(listOf(binding.four, binding.five, binding.six), R.drawable.horizontal)
            PlayerWin.ROW_3 -> Pair(listOf(binding.seven, binding.eight, binding.nine), R.drawable.horizontal)
            PlayerWin.COLUMN_1 -> Pair(listOf(binding.one, binding.four, binding.seven), R.drawable.vertical)
            PlayerWin.COLUMN_2 -> Pair(listOf(binding.two, binding.five, binding.eight), R.drawable.vertical)
            PlayerWin.COLUMN_3 -> Pair(listOf(binding.three, binding.six, binding.nine), R.drawable.vertical)
            PlayerWin.DIAGONAL_LEFT -> Pair(listOf(binding.one, binding.five, binding.nine), R.drawable.left_diagonal)
            PlayerWin.DIAGONAL_RIGHT -> Pair(listOf(binding.three, binding.five, binding.seven), R.drawable.right_diagonal)
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(GameActivity@ this, background)
        }
    }
    private fun disableBoxes() {
        binding.one.isEnabled = false
        binding.two.isEnabled = false
        binding.three.isEnabled = false
        binding.four.isEnabled = false
        binding.five.isEnabled = false
        binding.six.isEnabled = false
        binding.seven.isEnabled = false
        binding.eight.isEnabled = false
        binding.nine.isEnabled = false
    }
    private fun draw(): Boolean{
        return binding.one.background != null && binding.two.background != null && binding.three.background != null && binding.four.background != null && binding.five.background != null && binding.six.background != null && binding.seven.background != null && binding.eight.background != null && binding.nine.background != null
    }
}