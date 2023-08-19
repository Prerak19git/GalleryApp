package com.prerak.galleryandroid.ui.theme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsertDataProduct(dataProduct:DataProduct)

    @Delete
    suspend fun deleteDataProduct(dataProduct: DataProduct)

    @Query("DELETE FROM DataProduct")
    suspend fun deleteCompleteDatabase()

    @Query("Select * FROM DataProduct")
    suspend fun getTheDataBase():List<DataProduct>

    @Query("SELECT (SELECT COUNT(*) FROM DataProduct) == 0")
    suspend fun isEmpty(): Boolean



}