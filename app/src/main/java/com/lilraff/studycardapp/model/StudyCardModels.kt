package com.lilraff.studycardapp.model

import com.google.gson.annotations.SerializedName

// --- MODELOS DE AUTENTICAÇÃO ---
data class UserRequest(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UserResponse(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null
)

data class LoginResponse(
    val token: String,
    val user: UserResponse? = null,
    val name: String? = null
)

// --- MODELO DE TAREFA (TASK) ---
data class Task(
    @SerializedName("id")
    val id: String? = null,

    // Usamos alternate para aceitar tanto em português quanto em inglês
    @SerializedName("titulo", alternate = ["name"])
    val name: String? = null,

    @SerializedName("materia", alternate = ["theme"])
    val theme: String? = null,

    @SerializedName("descricao", alternate = ["text"])
    val text: String? = null,

    @SerializedName("dataEntrega", alternate = ["endDate"])
    val endDate: String? = null,

    @SerializedName("dataCriacao", alternate = ["createdAt", "startDate"])
    val createdAt: String? = null,

    @SerializedName("status")
    var status: String? = "PENDENTE"
) {
    val isCompleted: Boolean
        get() = status?.contains("CONCLUIDO", ignoreCase = true) == true || 
                status?.contains("CONCLUÍDO", ignoreCase = true) == true
}

// Objeto para criação
data class CardRequest(
    @SerializedName("titulo") val titulo: String,
    @SerializedName("materia") val materia: String,
    @SerializedName("descricao") val descricao: String,
    @SerializedName("dataEntrega") val dataEntrega: String
)

typealias CardResponse = Task
