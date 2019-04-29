package br.com.app_joplins

import java.io.Serializable

class Curso: Serializable {

    var id:Long = 0
    var nome = ""
    var foto = ""
    var ementa = ""

    override fun toString(): String {
        return "Curso(nome='$nome')"


    }




}