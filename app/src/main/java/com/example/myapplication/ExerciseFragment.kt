package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentExerciseBinding
import kotlinx.android.synthetic.main.fragment_exercise.*

/**
 * A simple [Fragment] subclass.
 */
class ExerciseFragment : Fragment() {

    private var restTimer:CountDownTimer?=null
    private var restProgress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentExerciseBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_exercise,
            container, false)
            setupRestView()



        return binding.root
    }

    private fun setupRestView() {


        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setRestProgressBar() {
        //progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased by 1
                //progressBar.progress = 10 - restProgress // Indicates progress bar progress
                tvTimer.text =
                    (10 - restProgress).toString()
            }

            override fun onFinish() {


            }
        }.start()
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

}
