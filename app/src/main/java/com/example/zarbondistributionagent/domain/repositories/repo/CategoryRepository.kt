package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.categorymodel.CategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories() : Flow<Result<List<CategoryData>?>>
}