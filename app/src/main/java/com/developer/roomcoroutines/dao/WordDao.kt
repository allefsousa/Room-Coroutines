package com.developer.roomcoroutines.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.developer.roomcoroutines.Word

@Dao
interface WordDao {
    @Insert
    suspend fun insert(word: Word)

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    fun deleteAll()

}