package com.example.kylin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.kylin.core.app.globeContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(
            this,
            "context is application ${globeContext() == application}",
            Toast.LENGTH_LONG
        ).show()
    }
}