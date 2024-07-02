package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var questions: Array<String>
    lateinit var options: Array<Array<String>>
    lateinit var correctAnswers: Array<Int>
    var questionIndex = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questions = resources.getStringArray(R.array.questions)
        options = arrayOf(
            resources.getStringArray(R.array.options1),
            resources.getStringArray(R.array.options2),
            resources.getStringArray(R.array.options3),
            resources.getStringArray(R.array.options4),
            resources.getStringArray(R.array.options5)
        )
        correctAnswers = resources.getIntArray(R.array.correct_answers)

        displayQuestion()

        btn_submit.setOnClickListener {
            val selectedOptionId = rg_options.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val radioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedIndex = rg_options.indexOfChild(radioButton)
                if (selectedIndex == correctAnswers[questionIndex]) {
                    score++
                }
                questionIndex++
                if (questionIndex < questions.size) {
                    displayQuestion()
                } else {
                    Toast.makeText(this, "Quiz finished! Your score: $score", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion() {
        val question = questions[questionIndex]
        val optionsArray = options[questionIndex]

        tv_question.text = question
        rb_option1.text = optionsArray[0]
        rb_option2.text = optionsArray[1]
        rb_option3.text = optionsArray[2]
        rb_option4.text = optionsArray[3]

        rg_options.clearCheck()
    }
}