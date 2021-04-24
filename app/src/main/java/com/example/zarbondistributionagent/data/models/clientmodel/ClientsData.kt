package com.example.zarbondistributionagent.data.models.clientmodel

data class ClientsData(
    val clients: List<Client>,
    val last_month_clients: List<Client>,
    val last_month_clients_count: Int
)