package com.lambdaschool.datapersistencesprintchallenge.viewmodel.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lambdaschool.datapersistencesprintchallenge.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class Movieatabase : RoomDatabase() {
    abstract fun MovieDBDao(): MovieDBDao
}


//the following is code from the AH last night I may want to refer to, it's here for ease of my reference
/*
@Database(entities =[Pokemon::class], exportSchema = true,version = 2)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        private var instance: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase? {
            if (instance == null) {
                synchronized(PokemonDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java, "pokemon_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance as PokemonDatabase
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    //Mock Data here
    class PopulateDbAsyncTask(db: PokemonDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val pokemonDao = db?.pokemonDao()

        override fun doInBackground(vararg p0: Unit?) {
            PokemonMockData.pokemonList.forEach {
                pokemonDao?.insert(it)
            }
        }
    }
}*/
