package com.e243768.ex_u3.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e243768.ex_u3.data.model.PokemonDetail
import com.e243768.ex_u3.data.model.PokemonEntity
import com.e243768.ex_u3.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonInfo = MutableStateFlow<PokemonDetail?>(null)
    val pokemonInfo: StateFlow<PokemonDetail?> = _pokemonInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getPokemonInfo(pokemonName)

            result.onSuccess { detail ->
                _pokemonInfo.value = detail
                checkIfFavorite(detail.id)
                _isLoading.value = false
            }
            result.onFailure {
                _isLoading.value = false
            }
        }
    }

    private fun checkIfFavorite(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(id)
        }
    }

    fun toggleFavorite() {
        val detail = _pokemonInfo.value ?: return

        viewModelScope.launch {
            val entity = PokemonEntity(
                id = detail.id,
                name = detail.name,
                imageUrl = detail.sprites.other.officialArtwork.frontDefault
                    ?: detail.sprites.frontDefault
                    ?: ""
            )

            if (_isFavorite.value) {
                repository.deleteFavorite(entity)
                _isFavorite.value = false
            } else {
                repository.insertFavorite(entity)
                _isFavorite.value = true
            }
        }
    }
}