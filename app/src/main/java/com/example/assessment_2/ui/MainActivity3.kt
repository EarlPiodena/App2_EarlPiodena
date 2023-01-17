package com.example.assessment_2.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment_2.R
import com.example.assessment_2.data.Message
import com.example.assessment_2.utils.BotResponse
import com.example.assessment_2.utils.Constants.OPEN_GOOGLE
import com.example.assessment_2.utils.Constants.OPEN_SEARCH
import com.example.assessment_2.utils.Constants.RECEIVE_ID
import com.example.assessment_2.utils.Constants.SEND_ID
import com.example.assessment_2.utils.Time
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.coroutines.*

class MainActivity3 : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Fred", "Daphne", "Velma", "Shaggy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customMessage("Hello! Today you are speaking with ${botList[random]}, how may I help you?")

    }

    private fun clickEvents() {
        btn_send.setOnClickListener() {
            sendMessages()
        }

        et_message.setOnClickListener() {
            GlobalScope.launch {
                delay(  100)
                withContext(Dispatchers.Main){
                    rv_messages.scrollToPosition(adapter.itemCount-1)
                }
            }

        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessages() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount-1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()
        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount-1)

                when(response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }
}