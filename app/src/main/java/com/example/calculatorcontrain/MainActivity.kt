package com.example.calculatorcontrain

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var display: TextView? = null
    private var currentOperator: String? = null
    private var firstNumber: Int? = null
    private var secondNumber:kotlin.Int? = null
    private var isOperatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Khởi tạo các nút bấm
        initializeButtons();
    }

    private fun initializeButtons() {
        val numberButtonIds = listOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9
        )

        numberButtonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onNumberClick(it as Button) }
        }

        findViewById<Button>(R.id.button_plus).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.button_minus).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.button_multiply).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.button_divide).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.button_equals).setOnClickListener { onEqualClick() }
        findViewById<Button>(R.id.button_c).setOnClickListener { clearDisplay() }
    }

    private fun onNumberClick(button: Button) {
        val number = button.text.toString()


        if (isOperatorPressed) {
            display?.text  = number
            isOperatorPressed = false
        } else {
            if (display?.text.toString() == "0") {
                display?.text = number
            } else {
                display?.append(number)
            }
        }
    }

    private fun onOperatorClick(operator: String) {
        currentOperator = operator
        firstNumber = display?.text.toString().toInt()
        isOperatorPressed = true
    }

    private fun onEqualClick() {
        if (firstNumber != null && currentOperator != null) {
            secondNumber = display?.text.toString().toInt()
            val result = when (currentOperator) {
                "+" -> firstNumber!! + secondNumber!!
                "-" -> firstNumber!! - secondNumber!!
                "*" -> firstNumber!! * secondNumber!!
                "/" -> firstNumber!! / secondNumber!!
                else -> 0.0
            }

            display?.text = result.toString()
            firstNumber = null
            secondNumber = null
            currentOperator = null
        }
    }

    private fun clearDisplay() {
        display?.text = "0"
        firstNumber = null
        secondNumber = null
        currentOperator = null
        isOperatorPressed = false
    }
}