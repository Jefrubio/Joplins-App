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
            val disciplina = Curso()
            disciplina.nome = nomeCurso.text.toString()
            disciplina.ementa = ementaCurso.text.toString()
            disciplina.professor = professorCurso.text.toString()
            disciplina.foto = urlFoto.text.toString()

            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Curso) {
        // Thread para salvar a discilpina
        Thread {
            CursoService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
