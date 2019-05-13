package br.com.app_joplins

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable


@Entity(tableName = "curso")
class Curso: Serializable {
    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var foto = ""
    var ementa = ""
    var professor = ""
    override fun toString(): String {
        return "Curso(nome='$nome')"


    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}