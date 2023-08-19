package com.prerak.galleryandroid.ui.theme

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import kotlinx.serialization.SerialName

@Database(
    entities = [DataProduct::class],
    version = 1
)
abstract class ProductsDataBase :RoomDatabase()
{
    abstract val dao : ProductDao
}


