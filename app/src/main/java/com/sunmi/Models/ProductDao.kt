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

//    @Query("SELECT DISTINCT flavor FROM productData WHERE category = 'FLAVORED FRIES'")
//    fun loadFlavored():List<Product>
//
//    @Query("SELECT DISTINCT flavor FROM productData WHERE category = 'FANCY FRIES'")
//    fun loadFancy()
//
//    @Query("SELECT DISTINCT size FROM productData WHERE category = 'BEVERAGES'")
//    fun loadDrinksSize()
//
//    @Query("SELECT DISTINCT size FROM productData WHERE category = 'FANCY FRIES'")
//    fun loadFancySize()
//
//    @Query("SELECT DISTINCT size FROM productData WHERE category = 'FLAVORED'")
//    fun loadFlavoredSize()

    @Update
    fun update(product: Product)

}