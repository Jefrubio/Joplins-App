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
import android.util.Log
import kotlinx.android.synthetic.main.login.*
import android.webkit.WebView
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {
    val USERNAME = "aluno"
    val PASSWORD = "impacta"

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //app_joplins.setImageResource(R.drawable.App_Joplins)

        btnlogin.setOnClickListener { onClickLogin() }
    }


    fun onClickLogin() {
        var campousuario = findViewById<TextView>(R.id.editUsuario)
        var camposenha = findViewById<TextView>(R.id.editSenha)
        val valorUsuario = campousuario.text.toString()
        val valorSenha = camposenha.text.toString()

        if (valorUsuario.equals(USERNAME) && valorSenha.equals(PASSWORD)) {
            val intent = Intent(context,TelaInicialActivity::class.java)
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
