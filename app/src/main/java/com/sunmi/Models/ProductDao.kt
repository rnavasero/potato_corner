package com.sunmi.Models

import android.arch.persistence.room.*

/**
 * Created by CDI on 3/1/18
 */
@Dao
interface ProductDao {

    @Query ("SELECT * FROM productData")
    fun loadAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("DELETE FROM productData")
    fun deleteAll()

    @Update
    fun update(product: Product)

}