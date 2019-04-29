package br.com.app_joplins

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.*
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*
import android.webkit.WebView
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {
    val CampoUsuario = "aluno"
    val CampoSenha = "impacta"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //App_Joplins.setImageResource(R.drawable.App_Joplins)

        btnlogin.setOnClickListener { onClickLogin() }
    }

    fun onClickLogin() {
        val campoUsuario = findViewById<EditText>(R.id.editUsuario)
        val campoSenha = findViewById<EditText>(R.id.editSenha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()

        if (valorUsuario.equals(CampoUsuario) && valorSenha.equals(CampoSenha)) {
            val intent = Intent(this, TelaInicialActivity::class.java)
            Toast.makeText(this, "logado", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        } else {
            // mensagem de erro
            Toast.makeText(
                this,
                "senha:$valorSenha ou usuario $valorUsuario login e/ou senha errado",
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}
