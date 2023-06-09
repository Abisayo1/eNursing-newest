package com.abisayo.computerize1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abisayo.computerize1.data.Constants
import com.abisayo.computerize1.databinding.ActivityCreateAccountBinding
import com.abisayo.computerize1.databinding.ActivityMainBinding
import com.abisayo.computerize1.models.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccount : AppCompatActivity() {
    private lateinit var database : DatabaseReference

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView8.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.button.setOnClickListener {
            val email = binding.editTextTextPassword.text.toString().trim()
            val pass = binding.editTextNumberPassword.text.toString().trim()
            val confirmPass = binding.editTextTextPassword2.text.toString().trim()
            val name = binding.email.text.toString().trim()




            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, FirstActivity::class.java)
                            intent.putExtra(Constants.NAME, name)
                            startActivity(intent)
                            finish()
                        }else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
        }else{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}