package br.com.app_joplins

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast



class CursoActivity : DebugActivity() {

    private val context: Context get() = this
    var curso: Curso? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_curso)


        // recuperar onjeto de Disciplina da Intent
        if (intent.getSerializableExtra("curso") is Curso)
        curso = intent.getSerializableExtra("curso") as Curso

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = curso?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeCurso)
        texto.text = curso?.nome
        var imagem = findViewById<ImageView>(R.id.cardImg)
        Picasso.with(this).load(curso?.foto).fit().into(imagem,
                object: Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
}
