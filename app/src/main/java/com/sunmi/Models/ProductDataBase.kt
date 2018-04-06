package com.example.codemagnus.newproject.Models

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by CDI on 3/1/18
 */
@Database(entities = [(Product::class)], version = 1)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: ProductDataBase? = null

        fun init(context: Context): ProductDataBase {
            if (INSTANCE == null) {
                synchronized(ProductDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ProductDataBase::class.java, "newproject.db").build()
                }
            }
            return INSTANCE as ProductDataBase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}