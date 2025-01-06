package com.example.passwordmanagerapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.passwordmanagerapp.data.AppDatabase
import com.example.passwordmanagerapp.data.Password
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun OnClickButtonValidate(view: View){
        val editTextAccount: EditText = findViewById<EditText>(R.id.editAccount)
        val editTextPassword: EditText = findViewById<EditText>(R.id.editPassword)
        val account = editTextAccount.text.toString() // Récupère le texte de l'EditText
        val pass = editTextPassword.text.toString()

        lifecycleScope.launch(Dispatchers.IO) {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "password_table"
            ).build()

            val save = db.passwordDao()
            save.insertPassword(Password(account = account, password = pass))

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Password saved!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun OnClickButtonSearch(view: View){

        // Récupère les références des EditText
        val editTextAccount: EditText = findViewById(R.id.editSearchAccount)
        val editTextPassword: EditText = findViewById(R.id.editSearchPassword)
        val account = editTextAccount.text.toString() // Récupère le texte de l'EditText (compte)

        // Démarrer une coroutine pour effectuer l'opération de récupération en arrière-plan
        lifecycleScope.launch(Dispatchers.IO) {
            // Créer la base de données Room
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "password_table"
            ).build()

            // Charger le DAO de mot de passe
            val passwordDao = db.passwordDao()

            // Récupérer le mot de passe pour le compte spécifié
            val passwordData = passwordDao.getPasswordForAccount(account)

            withContext(Dispatchers.Main) {
                // Vérifier si un mot de passe a été trouvé
                if (passwordData != null) {
                    // Afficher le mot de passe récupéré dans l'EditText (en tant que texte clair)
                    editTextPassword.setText(passwordData.password)
                } else {
                    // Si aucun mot de passe n'est trouvé, afficher un message (optionnel)
                    editTextPassword.setText("ERROR not found")
                    Toast.makeText(applicationContext, "Password not found", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }


}