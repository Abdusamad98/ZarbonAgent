package com.example.zarbondistributionagent.data.models.clientmodel

data class Client(
    val debt: Double,
    val id: Int,
    val image: String,
    val name: String,
    val phone_number1: String,
    val responsible_agent: String
)