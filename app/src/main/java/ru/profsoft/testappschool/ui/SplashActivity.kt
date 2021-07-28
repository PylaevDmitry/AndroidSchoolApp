package ru.profsoft.testappschool.ui

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val mAnimationDrawable = AnimationDrawable()

        mAnimationDrawable.isOneShot = true
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_0), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_1), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_2), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_3), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_4), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_5), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_6), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_7), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_8), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_9), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_10), 40)
        mAnimationDrawable.addFrame(resources.getDrawable(R.drawable.profsoft_11), 40)
        viewBinding.SplashImageView.background = mAnimationDrawable
        mAnimationDrawable.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val newIntent = Intent(this, MainActivity::class.java)
            startActivity(newIntent)
            finish()
        }, 1000)
    }
}