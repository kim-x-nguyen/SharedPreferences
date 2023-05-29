package com.kimdev.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var userName: EditText
    lateinit var userMessage: EditText
    lateinit var counter : Button
    lateinit var remember: CheckBox
    var count = 0

    var name : String? = null
    var message : String? = null
    var isRemember : Boolean? = null

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.editTextName)
        userMessage = findViewById(R.id.editTextMessage)
        counter = findViewById(R.id.button)
        remember = findViewById(R.id.checkBox)

        counter.setOnClickListener {
            count++
            counter.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        shareData()
    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }

    fun shareData () {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = userName.text.toString()
        message = userMessage.text.toString()
        isRemember = remember.isChecked

        val editor = sharedPreferences.edit()

        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putBoolean("key isRemember", isRemember!!)
        editor.putInt("key count", count)

        editor.apply()
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
    }

    fun retrieveData () {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name", null)
        message = sharedPreferences.getString("key message", null)
        isRemember = sharedPreferences.getBoolean("key isRemember", false)
        count = sharedPreferences.getInt("key count", 0)

        userName.setText(name)
        userMessage.setText(message)
        remember.isChecked = isRemember!!
        counter.text = count.toString()
    }
}