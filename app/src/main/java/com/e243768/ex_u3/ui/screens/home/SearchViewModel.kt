package com.e243768.ex_u3.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e243768.ex_u3.data.model.PokemonListEntry
import com.e243768.ex_u3.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonListEntry>>(emptyList())
    private val _fullPokemonList = MutableStateFlow<List<PokemonListEntry>>(emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    val pokemonList: StateFlow<List<PokemonListEntry>> = _pokemonList.asStateFlow()

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.getPokemonList(limit = 150, offset = 0)

            result.onSuccess { response ->
                _fullPokemonList.value = response.results
                _pokemonList.value = response.results
                _isLoading.value = false
            }
            result.onFailure {
                _errorMessage.value = "Error al cargar: ${it.message}"
                _isLoading.value = false
            }
        }
    }

    fun searchPokemon(query: String) {
        val listToSearch = _fullPokemonList.value
        if (query.isEmpty()) {
            _pokemonList.value = listToSearch
            return
        }
        val results = listToSearch.filter {
            it.name.contains(query.lowercase(Locale.ROOT))
        }
        _pokemonList.value = results
    }
}