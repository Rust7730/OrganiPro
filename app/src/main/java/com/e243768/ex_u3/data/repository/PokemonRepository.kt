package com.e243768.ex_u3.data.repository

import com.e243768.ex_u3.data.local.PokemonDao
import com.e243768.ex_u3.data.model.PokemonDetail
import com.e243768.ex_u3.data.model.PokemonEntity
import com.e243768.ex_u3.data.model.PokemonListResponse
import com.e243768.ex_u3.data.remote.PokeApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApiService,
    private val dao: PokemonDao
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonListResponse> {
        return try {
            val response = api.getPokemonList(limit, offset)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPokemonInfo(name: String): Result<PokemonDetail> {
        return try {
            val response = api.getPokemonInfo(name)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    val allFavorites: Flow<List<PokemonEntity>> = dao.getAllFavorites()

    suspend fun insertFavorite(pokemon: PokemonEntity) {
        dao.insertFavorite(pokemon)
    }

    suspend fun deleteFavorite(pokemon: PokemonEntity) {
        dao.deleteFavorite(pokemon)
    }

    suspend fun isFavorite(id: Int): Boolean {
        return dao.isFavorite(id)
    }
}