package com.example.uth_smart.google

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.uth_smart.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class LoginGoogle(private val context: Context) {
    private val tag = "LoginGoogle: "

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    private val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    fun getSignedInUser() = firebaseAuth.currentUser

    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    suspend fun firebaseAuthWithGoogle(data: Intent?): Boolean {
        return try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)!!
            val idToken = account.idToken!!
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential).await()
            Log.d(tag, "Firebase authentication successful.")
            true
        } catch (e: ApiException) {
            Log.w(tag, "Google sign in failed", e)
            false
        } catch (e: Exception) {
            Log.e(tag, "firebaseAuthWithGoogle general error: ${e.message}")
            false
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
        googleSignInClient.signOut()
        Log.d(tag, "User signed out.")
    }
}
