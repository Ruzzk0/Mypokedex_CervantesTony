package cervantes.fedra.mypokedex_cervantestony_244780

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class PokemonAdapter(context: Context, private val pokemonList: List<Pokemon>) :
    ArrayAdapter<Pokemon>(context, 0, pokemonList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_pokemon, parent, false)

        val pokemon = pokemonList[position]

        val imgPokemon = view.findViewById<ImageView>(R.id.iv_pokemon)
        val txtNumber = view.findViewById<TextView>(R.id.tv_number)
        val txtName = view.findViewById<TextView>(R.id.tv_name)

        Log.d("PokemonAdapter", "Mostrando pokemon: number=${pokemon.number}, name=${pokemon.name}")

        txtNumber.text = String.format("No. %03d", pokemon.number)
        txtName.text = pokemon.name

        Glide.with(context)
            .load(pokemon.imageUrl)
            .into(imgPokemon)

        return view
    }
}