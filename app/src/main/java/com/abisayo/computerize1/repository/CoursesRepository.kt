package com.abisayo.computerize1.repository

import androidx.lifecycle.MutableLiveData
import com.abisayo.computerize1.models.Course
import com.google.firebase.database.*

class CoursesRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Course")

    @Volatile private var INSTANCE : CoursesRepository ?= null

    fun getInstance() : CoursesRepository {
    return INSTANCE ?: synchronized(this) {

        val instance = CoursesRepository()
        INSTANCE = instance
        instance
    }

    }

    fun loadCourses(courseList : MutableLiveData<List<Course>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {

                    val _courseList : List<Course> = snapshot.children.map { dataSnapshot ->

                    dataSnapshot.getValue(Course::class.java)!!

                    }

                    courseList.postValue(_courseList)

                }catch (e: Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}