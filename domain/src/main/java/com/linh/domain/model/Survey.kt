package com.linh.domain.model

class Survey(
    val id: String,
    val title: String,
    val description: String,
    val questions: List<Question>,
)