package com.mads2202.gitclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mads2202.gitclient.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container,LoginFragment.newInstance()).commit()
    }
}