package com.example.sai.fireauth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {
        when(p0){
            btnLogin ->{
                shortToast("Login")
            }

            btnRegister ->{
                shortToast("Register")
            }
        }

    }

    private fun shortToast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, length).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initToolbar()
    }

    private fun init(){
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    private fun initToolbar(){
        val toolbar = findViewById(R.id.toolbarMain) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Login/Register"
    }
}
