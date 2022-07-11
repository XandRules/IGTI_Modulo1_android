package com.example.frases.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frases.data.Frase
import com.example.frases.data.repository.MemoryRepository

class MainViewModel: ViewModel() {

    private val memoryRepository: MemoryRepository = MemoryRepository(mutableListOf())

    private val _listaDeFrases = MutableLiveData<List<Frase>>()

    val listaDeFrases: LiveData<List<Frase>> = _listaDeFrases

    fun iniciarDados() {
        atualizarListaDeFrases()
    }

    fun salvarFrase(frase: Frase){
        Log.i("IGTIinfo", "Frase recebida $frase")

        memoryRepository.salvar(frase)

        atualizarListaDeFrases()
    }

    private fun atualizarListaDeFrases() {
        _listaDeFrases.value = memoryRepository.retornarLista()
    }

    fun limparListaDeFrases(){
        memoryRepository.limparLista()
        atualizarListaDeFrases()
    }

}