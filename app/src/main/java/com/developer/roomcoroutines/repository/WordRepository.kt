package com.developer.roomcoroutines.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.developer.roomcoroutines.Word
import com.developer.roomcoroutines.dao.WordDao

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread // garante que o metodo não execute em thread principal
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}