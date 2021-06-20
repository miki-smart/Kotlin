package com.samekengneering.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener {
    private var mCurrentPositions=1
    private var questionLists:ArrayList<Question>? =null
    private var mSelectedPosition:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        questionLists=Constants.getQuestions()
        setDefaultView()
        setQuestions()
        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
    }
    private fun selectedOptions(tv: TextView,selectedOption:Int){
        setDefaultView()

        mSelectedPosition=selectedOption
        tv.setTypeface( tv.typeface,Typeface.BOLD)
        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.background=ContextCompat.getDrawable(this@QuizQuestionsActivity,R.drawable.selector_drawable_shape_border_bg)
    }
    private fun setDefaultView(){
    var options=ArrayList<TextView>()
    options.add(0,tv_optionOne)
    options.add(1,tv_optionTwo)
    options.add(2,tv_optionThree)
    options.add(3,tv_optionFour)

    for ( option in options){
        option.setTextColor(Color.parseColor("#918888"))
        option.typeface= Typeface.DEFAULT
        option.background=ContextCompat.getDrawable(this@QuizQuestionsActivity,R.drawable.drawable_shape_bg)
    }
}
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_optionOne->{
                selectedOptions(tv_optionOne,1)
            }
            R.id.tv_optionTwo->{
                selectedOptions(tv_optionTwo,2)
            }
            R.id.tv_optionThree->{
                selectedOptions(tv_optionThree,3)
            }
            R.id.tv_optionFour->{
                selectedOptions(tv_optionFour,4)
            }
        }
    }

    private fun setQuestions(){

        var question:Question=questionLists!!.get(mCurrentPositions-1)
        progressBar.progress=mCurrentPositions
        tv_progresstext.text="$mCurrentPositions"+"/"+progressBar.getMax()
        tv_optionOne.text=question.optionOne
        tv_optionTwo.text=question.optionTwo
        tv_optionThree.text=question.optionThree
        tv_optionFour.text=question.optionFour
        tv_question.text=question.question
        iv_image.setImageResource(question.image)
    }
}