package com.example.frases.data.repository

import com.example.frases.data.Frase

class MemoryRepository(novalista: MutableList<Frase>) {
    private val listDb: MutableList<Frase> = novalista

    fun salvar(frase: Frase){
        listDb.add(frase)
    }

    fun limparLista(){
        listDb.clear()
    }

    fun retornarLista() = listDb.toList()

}