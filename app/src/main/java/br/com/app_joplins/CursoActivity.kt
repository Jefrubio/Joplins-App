package br.com.app_joplins

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class CursoActivity : DebugActivity() {

    var curso: Curso? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)

        // recuperar onjeto de Disciplina da Intent
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
        var texto = findViewById<TextView>(R.id.none)
        texto.text = curso?.nome
        var imagem = findViewById<ImageView>(R.id.image)
        Picasso.with(this).load(curso?.foto).fit().into(imagem,
                object: Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
}
