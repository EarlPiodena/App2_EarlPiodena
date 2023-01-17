package com.example.assessment_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener() {
            val name = userName.text.toString()

            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra("Username", name)
            startActivity(intent)

            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show();
        }
    }
}