package com.abisayo.computerize1.models

data class Course(var courseTitle: String ?= null, var note : String ?= null)

data class Scores(var studentName: String ?= null, var courseTitle: String ?= null, var studentScore : String ?= null)
