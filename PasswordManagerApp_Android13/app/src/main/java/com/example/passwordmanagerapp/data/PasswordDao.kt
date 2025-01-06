package com.example.passwordmanagerapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface PasswordDao {
    @Insert
    fun insertPassword(password: Password)

    @Query("SELECT * FROM password_table WHERE account = :account LIMIT 1")
    fun getPasswordForAccount(account: String): Password?

    @Query("SELECT * FROM password_table")
    fun getAllPasswords(): List<Password>

    @Delete
    fun deletePassword(password: Password)
}