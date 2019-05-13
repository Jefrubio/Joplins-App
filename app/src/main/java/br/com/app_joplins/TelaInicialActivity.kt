package br.com.app_joplins

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_curso.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var curso = listOf<Curso>()
    var recyclerCurso: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        //setSupportActionBar(toolbar)

        val args:Bundle? = intent.extras

        val nome = args?.getString("nome")


        val numero = intent.getIntExtra("nome",0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        mensagem.text = "Bem vindo $nome"

        val botaoSair = findViewById<Button>(R.id.botaoSair)
        botaoSair.setOnClickListener {cliqueSair()}

        val botaoCursos = findViewById<Button>(R.id.botaoCursos)
        botaoCursos.setOnClickListener {cliquecursos()}


        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        supportActionBar?.title = "Disciplinas"


        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        configuraMenuLateral()

        recyclerCurso = recyclerCurso
        recyclerCurso?.layoutManager = LinearLayoutManager (context)
        recyclerCurso?.itemAnimator = DefaultItemAnimator()
        recyclerCurso?.setHasFixedSize (true)


    }

    override fun onResume() {
        super.onResume()
        taskCursos()
    }

    fun taskCursos(){
        curso = CursoService.getCurso(context,)
        
        recyclerCurso?.adapter = CursoAdapter(curso)
        {onClickCursos(it)}

    }

    fun onClickCursos(curso: Curso){
        Toast.makeText(context, "${curso.nome}",
            Toast.LENGTH_SHORT).show()
    }

    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)


        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cursos -> {
                Toast.makeText(this, "Clicou Cursos", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_contato -> {
                Toast.makeText(this, "Clicou Contato", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_favorito -> {
                Toast.makeText(this, "Clicou Favoritos", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }


        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    fun cliquecursos(){
        val returnIntent = Intent();
        val it = Intent(this,Curso::class.java);
        startActivity(it);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId


        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }

        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }



}
