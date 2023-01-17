package com.example.assessment_2.utils

import com.example.assessment_2.utils.Constants.OPEN_GOOGLE
import com.example.assessment_2.utils.Constants.OPEN_SEARCH

object BotResponse {

    fun basicResponses(_message: String) : String {

        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when {

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello There!"
                    1 -> "What's up"
                    2 -> "Hey!"
                    else -> "error"
                }
            }

            //How are you
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing great, thanks for asking!"
                    1 -> "Kinda Hungry"
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            message.contains("flip") && message.contains("coin") -> {
                var r = (0..1).random()
                val result = if (r==0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            message.contains("solve") -> {
                val equation: String? = message.substringAfter("solve")

                return try{
                    val answer = SolveMath.solveMath(equation ?: "0")
                    answer.toString()

                } catch (e: Exception){
                    "Sorry, I can't solve that!"
                }
            }

            //Gets current time
            message.contains("time") && message.contains("?") -> {
                Time.timeStamp()
            }

            //Opens Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            //Opens Search
            message.contains("search") -> {
                OPEN_SEARCH
            }

            else -> {
                when (random) {
                    0 -> "I don't understand."
                    1 -> "I'm not sure what you are saying"
                    2 -> "Try asking something different!"
                    else -> "error"
                }
            }

        }
    }

}