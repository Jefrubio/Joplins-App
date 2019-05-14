package br.com.app_joplins
import android.content.Context
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL

object CursoService {

    //TROQUE PELA URL DE ONDE ESTÁ O WS
    // Veja um exemplo no repositório https://github.com/fesousa/aula-android-kotlin-api
    val host = "http://urldoseuservico.com.br"
    val TAG = "WS_LMSApp"

    fun getCursos (context: Context): List<Curso> {
        var cursos = ArrayList<Curso>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cursos"
            val json = HttpHelper.get(url)
            cursos = parserJson(json)
            // salvar offline
            for (d in cursos) {
                saveOffline(d)
            }
            return cursos
        } else {
            val dao = DatabaseManager.getCursoDAO()
            val curso = dao.findAll()
            return curso
        }

    }

    fun getCurso (context: Context, id: Long): List<Curso> {
        var curso = ArrayList<Curso>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cursos/${id}"
            val json = HttpHelper.get(url)
            curso = parserJson(json)

            return curso
        } else {
            val dao = DatabaseManager.getCursoDAO()
            val curso = dao.findAll()
            return curso
        }

    }

    fun save(curso: Curso): Response {
        val json = HttpHelper.post("$host/cursos", curso.toJson())
        return parserJson(json)
    }

    fun saveOffline(curso: Curso) : Boolean {
        val dao = DatabaseManager.getCursoDAO()

        if (! existeCurso(curso)) {
            dao.insert(curso)
        }

        return true

    }

    fun existeCurso(curso: Curso): Boolean {
        val dao = DatabaseManager.getCursoDAO()
        return dao.getById(curso.id) != null
    }

    fun delete(curso: Curso): Response {
        if (AndroidUtils.isInternetDisponivel(JoplinsApplication.getInstance().applicationContext)) {
            val url = "$host/cursos/${curso.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getCursoDAO()
            dao.delete(curso)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}