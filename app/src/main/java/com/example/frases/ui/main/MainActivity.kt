package com.example.frases.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.frases.data.Frase
import com.example.frases.databinding.ActivityMainBinding
import com.example.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val retornoFrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        activityResult ->
        if(activityResult.resultCode == RESULT_OK){
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)){
                    Log.i("IGTIinfo", "Retorno ${it.getParcelableExtra<Frase>(RETORNO)}")
                    viewModel.salvarFrase(it.getParcelableExtra(RETORNO) !! )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        iniciarDados()
        configurarListeners()
        configurarObservers()
    }

    private fun configurarObservers() {
        configurarAtualizacaoLista()
    }

    private fun configurarAtualizacaoLista() {
        viewModel.listaDeFrases.observe(this){ lista ->
            Log.i("IGTIinfo", "Observacao de lista $lista")
            atualizarLista(lista)
        }
    }

    private fun atualizarLista(lista: List<Frase>) {
        if (lista.isNullOrEmpty()){
            binding.rvListaFrases.visibility = View.GONE
            binding.tvMensagem.visibility = View.VISIBLE
        }else{
            binding.rvListaFrases.visibility = View.VISIBLE
            binding.tvMensagem.visibility = View.GONE
            binding.rvListaFrases.adapter = FrasesAdapter(listaDeFrases = lista)
        }
    }

    private fun iniciarDados() {
        viewModel.iniciarDados()
    }

    private fun configurarListeners() {
        configurarFabListeners()
    }

    private fun configurarFabListeners() {
        binding.fabAddNovaFrase.setOnClickListener(){
          Intent(this, IncluirFraseActivity::class.java).let {
                retornoFrase.launch(it)
            }

        }
    }

    companion object {
        const val RETORNO = "retorno_frase"
    }


}