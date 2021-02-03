package com.ivanasharov.news.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ivanasharov.news.R
import com.ivanasharov.news.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val mSplashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initLoad()
    }

    private fun initLoad() {
        mSplashViewModel.isLoadingFinish.observe(this, Observer{
            if (it){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        })
    }
}