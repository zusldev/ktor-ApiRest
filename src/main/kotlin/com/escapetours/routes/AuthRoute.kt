package com.escapetours.routes

import com.google.firebase.auth.FirebaseAuth
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.google.firebase.database.FirebaseDatabase

private const val BASE_URL = "http://192.168.1.7:8080/"

// Init Firebase


fun Routing.authRouting() {
    post("/auth/signup") {
        val email = call.receiveOrNull<String>("email")
        val password = call.receiveOrNull<String>("password")
        val name = call.receiveOrNull<String>("name")

        if (email != null && password != null && name != null) {
            try {
                val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
                val user = authResult.user
                val uid = user?.uid

                // Guardar el nombre del usuario en la base de datos o en Firestore utilizando el UID único
                // Ejemplo de código para guardar en Firebase Realtime Database:
                FirebaseDatabase.getInstance().getReference("users/$uid/name").setValue(name)

                call.respondText("Registro exitoso")
            } catch (e: Exception) {
                call.respondText("Error al registrar el usuario: ${e.message}")
            }
        } else {
            call.respondText("Datos de registro incompletos")
        }
    }

    post("/auth/login") {
        val email = call.receiveOrNull<String>("email")
        val password = call.receiveOrNull<String>("password")

        if (email != null && password != null) {
            try {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                call.respondText("Inicio de sesión exitoso")
            } catch (e: Exception) {
                call.respondText("Error al iniciar sesión: ${e.message}")
            }
        } else {
            call.respondText("Datos de inicio de sesión incompletos")
        }
    }

    post("/auth/logout") {
        FirebaseAuth.getInstance().signOut()
        call.respondText("Cierre de sesión exitoso")
    }
}

private fun FirebaseAuth.signInWithEmailAndPassword(email: Any, password: Any): Any {
    // TODO: Implement Firebase Auth signInWithEmailAndPassword method here and return the result of the operation
    return Unit

}

private fun FirebaseAuth.createUserWithEmailAndPassword(email: Any, password: Any): Any {
    // TODO: Implement Firebase Auth createUserWithEmailAndPassword method here and return the result of the operation
    return Unit
}
