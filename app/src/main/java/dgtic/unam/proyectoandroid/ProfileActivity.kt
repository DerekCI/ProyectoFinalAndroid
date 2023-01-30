package dgtic.unam.proyectoandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import dgtic.unam.proyectoandroid.databinding.ActivityMapsBinding
import dgtic.unam.proyectoandroid.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email?:"")


        //guardado de datos
        val prefs = getSharedPreferences("dgtic.unam.firebase.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()
    }

    private fun setup(email: String){
        title = "@string/perfilusuario"
        binding.correoEditText.setText(email)


    }
}

