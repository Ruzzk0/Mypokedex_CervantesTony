package cervantes.fedra.mypokedex_cervantestony_244780


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: PokemonAdapter
    private val pokemonList = mutableListOf<Pokemon>()
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        FirebaseApp.initializeApp(this)

        listView = findViewById(R.id.listView)
        adapter = PokemonAdapter(this, pokemonList)
        listView.adapter = adapter

        val btnRegistrar: Button = findViewById(R.id.btn_agregar)
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterPokemon::class.java)
            startActivity(intent)
        }

        loadPokemonFromFirestore()
    }

    private fun loadPokemonFromFirestore() {
        db.collection("pokemons")
            .orderBy("number")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e(TAG, "Error al escuchar cambios: ", e)
                    return@addSnapshotListener
                }

                try {
                    pokemonList.clear()
                    for (document in snapshots!!) {

                        Log.d(TAG, "Documento raw data: ${document.data}")

                        val numberValue = when (val num = document.get("number")) {
                            is Long -> num
                            is String -> num.toLongOrNull() ?: 0L
                            is Number -> num.toLong()
                            else -> 0L
                        }

                        val pokemon = Pokemon(
                            imageUrl = document.getString("imageUrl") ?: "",
                            name = document.getString("name") ?: "",
                            number = numberValue
                        )


                        Log.d(TAG, "Pokemon creado: $pokemon")

                        pokemonList.add(pokemon)
                    }

                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    Log.e(TAG, "Error al procesar documentos: ", e)
                }
            }
    }
}