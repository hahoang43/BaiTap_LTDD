package com.example.uth_smart

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uth_smart.google.LoginGoogle
import com.example.uth_smart.ui.screens.LoginScreen
import com.example.uth_smart.ui.screens.ProfileScreen
import com.example.uth_smart.ui.theme.UTH_SmartTheme
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val loginGoogle by lazy {
        LoginGoogle(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

    
        requestNotificationPermission()
        getFCMToken()

        setContent {
            UTH_SmartTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current

             
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = { result ->
                        coroutineScope.launch {
                            val success = loginGoogle.firebaseAuthWithGoogle(result.data)
                            if (success) {
                                navController.navigate("profile") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Sign in failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                      
                        LaunchedEffect(key1 = Unit) {
                            if (loginGoogle.getSignedInUser() != null) {
                                navController.navigate("profile") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        }

                        LoginScreen(
                            onGoogleSignInClick = {
                               
                                val signInIntent = loginGoogle.getSignInIntent()
                                launcher.launch(signInIntent)
                            }
                        )
                    }
                    composable("profile") {
                        val user = loginGoogle.getSignedInUser()
                        if (user == null) {
                        
                            LaunchedEffect(Unit) {
                                navController.navigate("login") {
                                    popUpTo("profile") { inclusive = true }
                                }
                            }
                        } else {
                            ProfileScreen(
                                user = user,
                                onBack = {
                                    loginGoogle.signOut()
                                    navController.navigate("login") {
                                        popUpTo("profile") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        // This is only necessary for API level 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                // If the permission is not granted, request it.
                val requestPermissionLauncher = registerForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        // FCM SDK (and your app) can post notifications.
                    } else {
                        // Inform user that they will not receive notifications.
                    }
                }
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                println("Fetching FCM registration token failed")
                return@addOnCompleteListener
            }


            val token = task.result

            val msg = "FCM Registration Token: $token"
            println(msg)
        }
    }
}