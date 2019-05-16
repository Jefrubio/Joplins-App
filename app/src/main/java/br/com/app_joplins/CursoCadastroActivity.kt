package br.com.app_joplins

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_curso.*
import kotlinx.android.synthetic.main.login.*

class CursoCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_curso)
        setTitle("Novo Curso")

        salvarCurso.setOnClickListener {
            val curso = Curso()
            curso.nome = nomeCurso.text.toString()
            curso.ementa = ementaCurso.text.toString()
            curso.professor = professorCurso.text.toString()
            curso.foto = urlFoto.text.toString()

            taskAtualizar(curso)
        }
    }

    private fun taskAtualizar(curso: Curso) {
        // Thread para salvar a discilpina
        Thread {
            CursoService.save(curso)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
