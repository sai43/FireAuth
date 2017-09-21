package com.example.sai.fireauth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initEvent()
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initEvent() {
        btnLogout.setOnClickListener{
            mAuth.signOut()
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser as FirebaseUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser) {
            val email = currentUser.email
            displayEmail.text = email
    }

    private fun shortToast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, length).show()
    }

}
