package com.example.bai2

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.ComponentActivity
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        buttonShow = findViewById(R.id.buttonShow)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            textViewError.text = ""
            val nText = editTextNumber.text.toString()

            // Kiểm tra dữ liệu đầu vào
            if (TextUtils.isEmpty(nText)) {
                textViewError.text = "Vui lòng nhập một số."
                return@setOnClickListener
            }

            val n = nText.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương lớn hơn 0."
                return@setOnClickListener
            }

            val numberList = when {
                radioEven.isChecked -> generateEvenNumbers(n)
                radioOdd.isChecked -> generateOddNumbers(n)
                radioSquare.isChecked -> generateSquareNumbers(n)
                else -> listOf()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
            listViewNumbers.adapter = adapter
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun generateSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { isPerfectSquare(it) }
    }

    private fun isPerfectSquare(num: Int): Boolean {
        val sqrtNum = sqrt(num.toDouble()).toInt()
        return sqrtNum * sqrtNum == num
    }
}
