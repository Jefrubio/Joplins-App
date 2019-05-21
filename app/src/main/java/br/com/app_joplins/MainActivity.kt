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

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.imagem_login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)



        val botaoLogin = findViewById<Button>(R.id.botao_login)

        // evento no botao de login forma 1
//        botaoLogin.setOnClickListener {
//            val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
//            val campoSenha = findViewById<EditText>(R.id.campo_senha)
//            val valorUsuario = campoUsuario.text.toString()
//            val valorSenha = campoSenha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }

        // segunda forma: delegar para método
        botaoLogin.setOnClickListener { onClickLogin() }

        progressBar.visibility = View.INVISIBLE

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }
    }

    fun onClickLogin() {

        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)
        // verificar se é para pembrar nome e senha
        if (checkBoxLogin.isChecked) {
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()
        params.putString("nome", "Joplins App")
        intent.putExtras(params)

        // enviar parâmetros simplificado
        intent.putExtra("numero", 10)

        // fazer a chamada
        //startActivity(intent)

        // fazer a chamada esperando resultado
        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        // abrir a disciplina caso clique na notificação com o aplicativo fechado
        abrirCurso()
        // mostrar no log o tokem do firebase
        Log.d("firebase", "Firebase Token: ${Prefs.getString("FB_TOKEN")}")
    }

    fun abrirCurso() {
        // verificar se existe  id da disciplina na intent
        if (intent.hasExtra("cursoId")) {
            Thread {
                var cursoId = intent.getStringExtra("cursoId")?.toLong()!!
                val curso = CursoService.getCurso(this, cursoId)
                runOnUiThread {
                    val intentCurso = Intent(this, CursoActivity::class.java)
                    intentCurso.putExtra("curso", curso[0])
                    startActivity(intentCurso)
                }
            }.start()
        }

    }

}
