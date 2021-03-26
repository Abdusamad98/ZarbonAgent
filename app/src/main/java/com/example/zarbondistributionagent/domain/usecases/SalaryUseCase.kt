package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData

interface SalaryUseCase {
    val errorNotResponseLiveData : LiveData<String>
    val errorResponseLiveData : LiveData<String>
    fun getSalary() : LiveData<SalaryData>

}