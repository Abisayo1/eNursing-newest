package com.abisayo.computerize1.models

data class Course(var courseTitle: String ?= null, var note : String ?= null)

data class Track(var studentName: String ?= null, val userId: String?= null, var timeStamp : String ?= null)

data class Student(var name: String ?= null, val userId: String?= null)

data class Scores(var studentName: String ?= null, val userId: String?= null, var timeStamp : String ?= null)
