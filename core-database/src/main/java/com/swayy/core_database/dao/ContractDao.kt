package com.swayy.core_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.swayy.core_database.model.ContractEntity

@Dao
interface ContractDao {

    @Insert
    suspend fun insertContracts(contractEntity: ContractEntity)

    @Query("SELECT * FROM Contract_Table")
    fun getContracts():LiveData<List<ContractEntity>>

    @Delete
    suspend fun deleteContracts(contractEntity: ContractEntity)
}