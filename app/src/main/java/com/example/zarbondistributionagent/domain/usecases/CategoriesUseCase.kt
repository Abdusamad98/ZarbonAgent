package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.categorymodel.CategoryData

interface CategoriesUseCase {
    val errorCategoriesLiveData : LiveData<Unit>
    fun getCategories() : LiveData<List<CategoryData>>
}