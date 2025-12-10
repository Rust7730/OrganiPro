package com.e243768.ex_u3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e243768.ex_u3.data.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}