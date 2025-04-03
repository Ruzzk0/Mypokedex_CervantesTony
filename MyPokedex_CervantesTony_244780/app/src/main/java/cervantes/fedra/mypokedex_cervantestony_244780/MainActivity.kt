package cervantes.fedra.mypokedex_cervantestony_244780

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val registerPokemon : Button = findViewById(R.id.addPokemon)

        registerPokemon.setOnClickListener {
            val intent: Intent = Intent(this, RegisterPokemon::class.java)
            startActivity(intent)
        }

    }



}