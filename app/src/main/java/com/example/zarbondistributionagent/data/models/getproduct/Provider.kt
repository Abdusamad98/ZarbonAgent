package com.example.zarbondistributionagent.data.models.getproduct

data class Provider(
    val INN: Int,
    val account_number: Int,
    val address: String,
    val bank: String,
    val bank_code: Int,
    val created_date: String,
    val director: String,
    val id: Int,
    val name: String,
    val phone_number1: String,
    val phone_number2: String,
    val responsible_agent: String
)