package com.example.frases.ui.incluirfrase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.frases.R
import com.example.frases.data.Frase
import com.example.frases.databinding.ActivityIncluirFraseBinding
import com.example.frases.databinding.ActivityMainBinding
import com.example.frases.ui.main.MainActivity

class IncluirFraseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncluirFraseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        configurarListeneres()
    }

    private fun configurarListeneres() {
        configurarListeneresBtnCancelar()
        configurarListeneresBtnSalvar()

    }

    private fun configurarListeneresBtnSalvar() {
        binding.btnSalvar.setOnClickListener(){
            salvarFrase()
        }
    }

    private fun salvarFrase() {
        val autor = binding.autoresFraseED.text.toString()
        val frase = binding.frasesET.text.toString()

        if (autor.isEmpty()){
            binding.autoresFraseTIL.error = getString(R.string.erro_sem_autor)
        }else{
            binding.autoresFraseTIL.error = null
        }

        if(frase.isEmpty()){
            binding.fraseTIL.error = getString(R.string.erro_sem_frase)
        }else{
            binding.fraseTIL.error = null
        }

        if(autor.isNotEmpty() && frase.isNotEmpty()){
            Log.i("IGTIinfo","Autor $autor Frase $frase")
            Intent().apply {
                putExtra(
                    MainActivity.RETORNO,
                    Frase(autor = autor, frase = frase)
                )
                setResult(RESULT_OK, this)
            }
            finish()
        }
        else{
            Log.i("IGTIinfo", "Dados não salvos")
        }
    }

    private fun configurarListeneresBtnCancelar() {
        binding.btnCancelar.setOnClickListener(){
            finish()
        }
    }
}