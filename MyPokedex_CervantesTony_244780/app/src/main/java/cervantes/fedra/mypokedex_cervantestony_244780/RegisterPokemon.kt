package cervantes.fedra.mypokedex_cervantestony_244780

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cervantes.fedra.mypokedex_cervantestony_244780.R.*

class RegisterPokemon : AppCompatActivity() {

    val REQUEST_IMAGE_GET = 1
    val CLOUD_NAME = "dawy0qqy5"
    val UPLOAD_PRESET = "pokemon-upload"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_register_pokemon)

        val name: EditText = findViewById(R.id.pokemonName)
        val pNumber: EditText = findViewById(R.id.pokemonNumber)
        val select: Button = findViewById(R.id.selectImage)
        val save: Button = findViewById(R.id.savePokemon)

        select.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image"
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK){
            val fullImageuri: Uri? = data?.data

            if(fullImageuri !=null){
                changeImage(fullImageuri)
            } w
        }
    }


    fun changeImage(uri: Uri){
        val thumbanail: ImageView = findViewById(R.id.thumbnail)
        try {
            thumbanail.setImageURI(uri)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}