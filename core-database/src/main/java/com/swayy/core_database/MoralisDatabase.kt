package com.swayy.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swayy.core_database.converters.Converters
import com.swayy.core_database.dao.ContractDao
import com.swayy.core_database.model.ContractEntity

@Database(
    entities = [
        ContractEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MoralisDatabase:RoomDatabase() {
    abstract val contractDao:ContractDao
}