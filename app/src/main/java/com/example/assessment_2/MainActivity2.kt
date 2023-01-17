package com.example.assessment_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assessment_2.ui.MainActivity3
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = getIntent()
        val name = intent.getStringExtra("Username").toString()

        textView.text= "Welcome! " + name

        loginButton1.setOnClickListener() {
            val intent2 = Intent(this@MainActivity2, MainActivity3::class.java)
            intent.putExtra("Username", name)
            startActivity(intent2)
        }
    }
}