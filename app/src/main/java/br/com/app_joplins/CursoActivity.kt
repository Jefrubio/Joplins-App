package br.com.app_joplins

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast



class CursoActivity : DebugActivity() {

    private val context: Context get() = this
    private var cursos = listOf<Curso>()
    //var curso: Curso? = null
    var recyclerCurso: RecyclerView? = null
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_curso)

        recyclerCurso = findViewById<RecyclerView>(R.id.recyclerCurso)
        recyclerCurso?.layoutManager = LinearLayoutManager(context)
        recyclerCurso?.itemAnimator = DefaultItemAnimator()
        recyclerCurso?.setHasFixedSize(true)

        // recuperar onjeto de Disciplina da Intent
//        if (intent.getSerializableExtra("curso") is Curso)
//            curso = intent.getSerializableExtra("curso") as Curso
        val args:Bundle? = intent.extras
        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Cursos"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
//        var texto = findViewById<TextView>(R.id.nomeCurso)
//        texto.text = curso?.nome
//        var imagem = findViewById<ImageView>(R.id.cardImg)
//        Picasso.with(this).load(curso?.foto).fit().into(imagem,
//                object: Callback{
//                    override fun onSuccess() {}
//
//                    override fun onError() { }
//                })
    }
    override fun onResume() {
        super.onResume()
        taskCursos()
    }

    fun taskCursos() {

        // Criar a Thread
        Thread {
            // Código para procurar as disciplinas
            // que será executado em segundo plano / Thread separada
            this.cursos = CursoService.getCursos(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerCurso?.adapter = CursoAdapter(this.cursos) { onClickCursos(it) }
                // enviar notificação
                enviaNotificacao(this.cursos.get(0))

            }
        }.start()

    }
    fun enviaNotificacao(curso: Curso) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, CursoActivity::class.java)
        // parâmetros extras
        intent.putExtra("curso", curso)
        // Disparar notificação
        NotificationUtil.create(this, 1, intent, "Joplins", "Você tem nova atividade na ${curso.nome}")
    }



    // tratamento do evento de clicar em uma disciplina
    fun onClickCursos(curso: Curso) {
        Toast.makeText(context, "Clicou curso ${curso.nome}", Toast.LENGTH_SHORT).show()
//        val intent = Intent(context, CursoActivity::class.java)
//        intent.putExtra("curso", curso)
//        startActivity(intent)
    }

}

