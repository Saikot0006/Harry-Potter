package com.example.harrypotter.ui.characterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.models.CharactersListResponse
import com.example.harrypotter.ui.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel(){
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }
    val isLoading : LiveData<Boolean> = _isLoading

    private val _characterList = MutableLiveData<List<CharactersListResponse.CharactersListResponseItem>>()
    val characterList : LiveData<List<CharactersListResponse.CharactersListResponseItem>> = _characterList
    fun getCharacters(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.getCharacters()
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        if(response.isSuccessful){
                            if(!response.body().isNullOrEmpty()){
                                _characterList.value = response.body()
                            }else{
                                _characterList.value = listOf()
                            }
                        }else{
                            _characterList.value = listOf()
                        }
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        _isLoading.value = false
                        _characterList.value = listOf()
                    }
                }
            }
        }catch (e: Exception){
            _isLoading.value = false
            _characterList.value = listOf()
            Log.d("localizedMessage", "getCharacters: ${e.localizedMessage}")
        }
    }
}