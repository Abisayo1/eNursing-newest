package com.abisayo.computerize1.data

import android.app.Activity
import android.content.Intent
import com.abisayo.computerize1.OxygenTherapy.OxygenTherapyFlashCardActivity
import com.abisayo.computerize1.admission.AdmissionFlashcardActivity
import com.abisayo.computerize1.assessment.AssessmentFlashCardActivity
import com.abisayo.computerize1.communication.CommunicationFlashcardActivity
import com.abisayo.computerize1.discharge.DischargeFlashcardActivity
import com.abisayo.computerize1.flashcard.FlashCard
import com.abisayo.computerize1.historyOfNursing.HistoryFlashcardActivity
import com.abisayo.computerize1.oxygenation.OxygenationFlashCardActivity
import com.abisayo.computerize1.roles.RolesFlashcardActivity
import com.abisayo.computerize1.trendsInNursing.TrendsFlashcardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.functions.FirebaseFunctions


fun Activity.startHistoryFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, HistoryFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startTrendsFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, TrendsFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startRolesFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, RolesFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startAssessmentFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, AssessmentFlashCardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startAdmissionFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, AdmissionFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startDischargeFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, DischargeFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startCommunicationFlashcardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, CommunicationFlashcardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startOxygenationFlashCardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, OxygenationFlashCardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun Activity.startOxygenTherapyFlashCardActivity(flashCards: ArrayList<FlashCard>){
    val activityIntent =
        Intent(this, OxygenTherapyFlashCardActivity::class.java)
    activityIntent.putExtra(Constants.EXTRA_FLASH_CARD, flashCards)
    startActivity(activityIntent)
}

fun logUserLogin() {
    val functions = FirebaseFunctions.getInstance()
    FirebaseAuth.getInstance().addAuthStateListener { auth ->
        if (auth.currentUser != null) {
            val user = auth.currentUser
            val uid = user?.uid
            val displayName = user?.displayName
            val email = user?.email
            val timestamp = System.currentTimeMillis()
            val userLogin = hashMapOf(
                "uid" to uid,
                "displayName" to displayName,
                "email" to email,
                "timestamp" to timestamp
            )
            functions.getHttpsCallable("logUserLogin").call(userLogin)
                .addOnSuccessListener { result ->
                    // Handle success
                }
                .addOnFailureListener { e ->
                    // Handle error
                }
        }
    }
}
