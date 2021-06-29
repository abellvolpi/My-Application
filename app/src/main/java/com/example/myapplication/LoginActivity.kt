package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.utils.Csv
import com.example.myapplication.utils.Tests

const val EXTRA_CANDIDATO = "CANDIDATO"

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var buttonLogin: View
    private lateinit var textbutton: TextView
    private lateinit var progress: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username_field)
        password = findViewById(R.id.password_field)
        buttonLogin = findViewById(R.id.buttonLogin)
        progress = findViewById(R.id.progress)
        textbutton = findViewById(R.id.label_login_id)

        buttonLogin.setOnClickListener {
            changeState(true)
            delay {
                efetuaLogin(username.text.toString(), password.text.toString())?.let {
                    val intencao = Intent(this, MainActivity::class.java).apply {
                        putExtra(EXTRA_CANDIDATO, it)
                    }
                    startActivity(intencao)
                    finish()
                } ?: run {
                    loginInvalido()

                    changeState(false)

                }
            }
        }

//        testMyArrayList()
        testMyLinkedList()

    }

    private fun buttonFunctions() {
        buttonLogin.isClickable = false
        buttonLogin.alpha = 0.5F
        textbutton.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    private fun delay(delay: Long = 1500, action: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(action, delay)
    }


    private fun changeState(isLoading: Boolean) {
        if (isLoading) {
            progress?.visibility = View.VISIBLE
            buttonLogin.apply {
                isClickable = false
                isFocusable = false
                alpha = 0.5f
            }
        } else {
            progress?.visibility = View.GONE
            buttonLogin.apply {
                isClickable = true
                isFocusable = true
                alpha = 1f
            }
        }
    }

    // Função que lê o usário do edittext e verifica com a list do csv, caso ache, retorna o candidato
    private fun efetuaLogin(user_informed: String, password_informed: String): Candidato? {
        val list = Csv.fetchCandidates(this)
        list.forEach {
            if (it.username == user_informed && it.password.toString() == password_informed) {
                return it
            }
        }
        return null
    }

    private fun loginInvalido() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Erro")
        builder.setMessage("Usuário ou senha inválidos")
        builder.setNeutralButton("OK") { _, _ -> }
        val caixa_dialogo: AlertDialog = builder.create()
        caixa_dialogo.show()

    }

    // botao de voltar aparece um aviso na home pra confirmar
    //arrumar layout
//    override fun onBackPressed() {
//
//
//        super.onBackPressed()
//    }
    private fun testMyArrayList() {
        val simpleArrayList = MyArrayList<String>()
        val (passed, message) = Tests.testPsListImplementation(simpleArrayList, false)
        Toast.makeText(this, if (passed) "All tests passed" else "Test failed with: ${message ?: ""}", Toast.LENGTH_LONG).show()
    }

    private fun testMyLinkedList() {
        val simpleLinkedList = MyLinkedList<String>()
        val (passed, message) = Tests.testPsListImplementation(simpleLinkedList, true)
        Toast.makeText(this, if (passed) "All tests passed" else "Test failed with: ${message ?: ""}", Toast.LENGTH_LONG).show()
    }




}
