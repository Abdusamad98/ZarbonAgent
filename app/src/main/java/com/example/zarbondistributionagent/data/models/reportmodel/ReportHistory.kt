package com.example.zarbondistributionagent.data.models.reportmodel

data class ReportHistory(
    val comment: String,
    val id: Int,
    val image: String?,
    val created_date :String,
    val sale_agent: Int
)