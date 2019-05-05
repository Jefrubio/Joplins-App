package br.com.app_joplins

import android.arch.persistence.room.Room


object DatabaseManager {

    // singleton
    private var dbInstance: JoplinsDatabase
    init {
        val appContext = JoplinsApplication.getInstance().applicationContext
        //val appContext = JoplinsDatabase.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
            JoplinsDatabase::class.java, // Referência da classe do banco
                "joplins.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getCursoDAO(): CursoDAO {
        return dbInstance.cursoDAO()
    }
}