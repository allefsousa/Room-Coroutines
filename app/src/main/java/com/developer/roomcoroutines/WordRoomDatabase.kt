package com.developer.roomcoroutines

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.developer.roomcoroutines.dao.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class],version = 1)
public abstract class WordRoomDatabase:RoomDatabase(){
    abstract fun wordDao():WordDao
//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    populateDatabase(database.wordDao())
//                }
//            }
//        }
//
//        private suspend fun populateDatabase(wordDao: WordDao) {
//            wordDao.deleteAll()
//
//            var word = Word("Hello","Allef")
//            wordDao.insert(word)
//            word = Word("World!","Allef")
//            wordDao.insert(word)
//        }
//    }




    // singleton para evitar variasa instancias do banco de dados
    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context,scope:CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Word_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}