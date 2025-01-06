package com.example.passwordmanagerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password_table")
data class Password(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID unique pour chaque mot de passe
    val account: String, // Nom du compte (exemple : Gmail, Facebook)
    val password: String // Mot de passe correspondant
)