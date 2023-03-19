package com.abisayo.computerize1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.abisayo.computerize1.Games.GameClaraActivity
import com.abisayo.computerize1.data.Constants
import com.abisayo.computerize1.databinding.ActivityFirstBinding
import com.abisayo.computerize1.models.Scores
import com.abisayo.computerize1.models.Student
import com.abisayo.computerize1.models.Track
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class FirstActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference


    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val name = intent.getStringExtra(Constants.NAME)
        val userID = FirebaseAuth.getInstance().currentUser?.uid

        // Create an instance of SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Get an editor for the SharedPreferences
        val editor = sharedPreferences.edit()

       // Put some data in the SharedPreferences
        editor.putString("username", name)
        editor.apply()





        val username = sharedPreferences.getString("username", "")




        val calendar = Calendar.getInstance()
        val seconds = calendar.get(Calendar.SECOND)
        val minutes = calendar.get(Calendar.MINUTE)
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)

        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        val formattedTime = timeFormat.format(calendar.time)

        val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm")
        val currentDate:String = sdf.format(Date())

        val time = "$currentDate, $dayOfMonth, $year"

        println("Seconds: $seconds")
        println("Minutes: $minutes")
        println("Hours: $hours")
        println("Day of month: $dayOfMonth")
        println("Year: $year")








        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        val databases = FirebaseDatabase.getInstance().reference
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        database = FirebaseDatabase.getInstance().getReference("Student")
        database.child("$uid").get().addOnSuccessListener {

            if (it.exists()){

                val firstname = it.child("name").value

                //Toast.makeText(this,"$firstname",Toast.LENGTH_SHORT).show()
                savedData("$firstname")

                saveData("$firstname", "$userID", "$currentDate")


            }else{
                val name = intent.getStringExtra(Constants.NAME)
                savedData("$name")
                saveData("$name", "$userID", "$currentDate")


            }

        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }



            binding.course.setOnClickListener {
            val intent = Intent(this, TopicsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

        }

       binding.game.setOnClickListener {
            val intent = Intent(this, GameClaraActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, EnterAdminPasswordActivity::class.java)
            startActivity(intent)
        }

       binding.puzzle.setOnClickListener {
            val intent = Intent(this, PuzzleActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

        }
    }

    private fun saveData(name: String, userID: String, time: String) {

        database = FirebaseDatabase.getInstance().getReference("Track")
        val Track = Track(name, userID, time)

            database.child(userID).setValue(Track).addOnSuccessListener {

            }.addOnFailureListener {

            }
        }

    private fun savedData(name: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        database = FirebaseDatabase.getInstance().getReference("Student")
        val Student = Student(name, uid)

        database.child("$uid").setValue(Student).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }

}