package com.example.sai.fireauth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var sharedPreferences: SharedPreferences

    override fun onClick(p0: View?) {
        when(p0){
            btnLogin ->{
                val  email: String = edtEmail.text.toString().trim()
                val  password: String = edtPassword.text.toString().trim()
                if(!Check()){ return }
                Login(email, password)
            }

            btnRegister ->{
                val  email: String = edtEmail.text.toString().trim()
                val  password: String = edtPassword.text.toString().trim()
                if(!Check()){ return }
                Register(email, password)

            }
        }

    }

    private fun Check(): Boolean {
        var check: Boolean = true
        val  email: String = edtEmail.text.toString().trim()
        if(TextUtils.isEmpty(email)){
            edtEmail.setError("Please Enter Email")
            check = false
        }
        val  password: String = edtPassword.text.toString().trim()
        if(TextUtils.isEmpty(password)){
            edtPassword.setError("Please Enter Password")
            check = false
        }
        return check
    }

    private fun Register(email:String, password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener {
                if(it.isSuccessful){
                   shortToast("Register Success")
                }else{
                    shortToast("Register Failed")
                }
        }
    }

    private fun Login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isComplete){
                        shortToast("Login Success")
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        if(chkSave.isChecked){
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.putBoolean("check", true)
                            editor.commit()
                        }else{
                            editor.remove("email")
                            editor.remove("password")
                            editor.remove("check")
                            editor.commit()
                        }
                        route()
                    }else{
                        shortToast("Login Failed")
                    }
                }

    }

    private fun route() {
        startActivity(Intent(MainActivity@this, Main2Activity::class.java))
    }

    private fun shortToast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, length).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initToolbar()
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        edtEmail.setText(sharedPreferences.getString("email", ""))
        edtPassword.setText(sharedPreferences.getString("password", ""))
        chkSave.isChecked = sharedPreferences.getBoolean("check", false)
        mAuth = FirebaseAuth.getInstance()
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
