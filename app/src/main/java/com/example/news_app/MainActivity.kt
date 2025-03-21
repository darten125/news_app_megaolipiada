package com.example.news_app

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.news_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = MainPresenter()

        usernameEditText = binding.usernameEditText
        passwordEditText = binding.passwordEditText


        binding.enterButton.setOnClickListener {
            if(usernameEditText.text.isNotEmpty() &&
                passwordEditText.text.isNotEmpty() &&
                presenter.checkPasswordValidation(passwordEditText.text)){
                val intent = Intent(this,NewsActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Некорректный ввод данных",Toast.LENGTH_LONG).show()
            }
        }

    }
}