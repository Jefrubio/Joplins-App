package br.com.app_joplins

import android.content.Context

object CursoService {
    fun getCurso (context: Context): List<Curso>{
        val curso = mutableListOf<Curso>()

        for (i in 1..10) {
            val d = Curso()
            d.nome = "cursos $i"
            d.ementa = "emanta $i"
            d.foto = "https://cdn.pixabay.com/photo/2016/07/12/19/08/laptop-1512838_960_720.png"
            curso.add(d)
                        }
            return curso
        }
    }






