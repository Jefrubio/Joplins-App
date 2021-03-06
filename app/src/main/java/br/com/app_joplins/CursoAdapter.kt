package br.com.app_joplins

import android.content.DialogInterface
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_curso.view.*

class CursoAdapter(
    val curso: List<Curso>,
    val onClick: (Curso) -> Unit ): RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    class CursoViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val cardNome: TextView
        val cardImage: ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.cardNome
            cardImage = view.cardImg
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.card_curso

        }
    }

    override fun getItemCount()= this.curso.size
        //return this.curso.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_curso, parent, false)
        val holder = CursoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {

        val context = holder.itemView.context
        val curso = curso[position]




        holder.cardNome.text = curso.nome
        holder.cardProgress.visibility = View.VISIBLE
        Picasso.with(context).load(curso.foto).fit().into(holder.cardImage,
            object: com.squareup.picasso.Callback{

                override fun onSuccess() {
                    holder.cardImage.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardImage.visibility = View.GONE

                }
            })


        holder.itemView.setOnClickListener { onClick(curso) }



    }
}