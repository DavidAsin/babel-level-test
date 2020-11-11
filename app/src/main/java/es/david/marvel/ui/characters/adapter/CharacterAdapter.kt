package es.david.marvel.ui.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.david.marvel.R
import es.david.marvel.data.network.response.get_characters.Result
import kotlinx.android.synthetic.main.item_character.view.*


class CharacterAdapter(private val onClick: (Result) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var charactersList: MutableList<Result> = mutableListOf()

    //Initiate and inflate the cart_menu view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(v)
    }

    //Binds each data to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(charactersList[position], onClick)
    }

    //Holder class holding and initiating all the views
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(character: Result, onClick: (Result) -> Unit) {
            itemView.characterName.text = character.name
            Picasso.get().load("${character.thumbnail.path}.${character.thumbnail.extension}")
                .fit()
                .centerCrop()
                .into(itemView.imageView)
            itemView.setOnClickListener {
                onClick(character)
            }
        }

    }

    fun updateData(newData: List<Result>) {
        charactersList.addAll(newData)
        notifyDataSetChanged()
    }

    //Returns the size of news list
    override fun getItemCount(): Int {
        return charactersList.size
    }

}

