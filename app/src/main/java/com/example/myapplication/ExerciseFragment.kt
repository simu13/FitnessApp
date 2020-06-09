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

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_exercise,
            container, false
        )
        exerciseList=Constants.defaultExerciseList()
        setupRestView()


        return binding.root
    }

     override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        super.onDestroy()
    }

    /**
     * Function is used to set the timer for REST.
     */
    private fun setupRestView() {

        // Here according to the view make it visible as this is Rest View so rest view is visible and exercise view is not.
        llRestView?.visibility = View.VISIBLE
        llExerciseView?.visibility = View.GONE

        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition+1].getName()

        // This function is used to set the progress details.
        setRestProgressBar()
    }

    /**
     * Function is used to set the progress of timer using the progress
     */
    private fun setRestProgressBar() {

        //llRestView.visibility = View.VISIBLE
        //llExerciseView.visibility = View.GONE

        progressBar?.progress = restProgress // Sets the current progress to the specified value.


        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased to ascending order
                progressBar.progress = 10 - restProgress // Indicates progress bar progress
                tvTimer.text =
                    (10 - restProgress).toString()  // Current progress is set to text view in terms of seconds.
            }

            override fun onFinish() {
                // TODO(Step 6 - Increasing the current position of the exercise after rest view.)
                // START
                currentExercisePosition++
                // END

                setupExerciseView()
            }
        }.start()
    }

    /**
     * Function is used to set the progress of timer using the progress for Exercise View.
     */
    private fun setupExerciseView() {

        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE

        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        // TODO(Step 7 - Setting up the current exercise name and image to view to the UI element.)
        // START
        /**
         * Here current exercise name and image is set to exercise view.
         */
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
        // END

        setExerciseProgressBar()
    }

    /**
     * Function is used to set the progress of timer using the progress for Exercise View for 30 Seconds
     */
    private fun setExerciseProgressBar() {

        progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                // TODO(Step 8 - Updating the view after completing the 30 seconds exercise.)
                // START
                if (currentExercisePosition < 11) {
                    setupRestView()
                } else {



                }
            }
        }.start()
    }
}