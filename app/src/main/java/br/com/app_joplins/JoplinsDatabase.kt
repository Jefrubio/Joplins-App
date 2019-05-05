package br.com.app_joplins

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Curso::class), version = 1)
abstract class JoplinsDatabase: RoomDatabase() {
    abstract fun cursoDAO(): CursoDAO
}