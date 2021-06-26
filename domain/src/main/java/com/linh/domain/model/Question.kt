package com.linh.domain.model

class Question(
    val id:String,
    val text: String,
    val questions: List<Answer>,
)