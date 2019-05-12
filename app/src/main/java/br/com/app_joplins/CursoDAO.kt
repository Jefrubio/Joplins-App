package br.com.app_joplins

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CursoDAO {
    @Query("SELECT * FROM curso where id = :id")
    fun getById(id: Long) : Curso?

    @Query("SELECT * FROM curso")
    fun findAll(): List<Curso>

    @Insert
    fun insert(disciplina: Curso)

    @Delete
    fun delete(disciplina: Curso)

}