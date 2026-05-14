package com.lilraff.studycardapp.network

import com.lilraff.studycardapp.model.*
import retrofit2.Response
import retrofit2.http.*

interface StudyCardApi {

    @POST("users")
    suspend fun register(@Body request: UserRequest): Response<Unit>

    // Tentando o endpoint login sem o prefixo auth
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("cards")
    suspend fun getCards(@Header("Authorization") token: String): Response<List<CardResponse>>

    @POST("cards")
    suspend fun createCard(@Header("Authorization") token: String, @Body request: CardRequest): Response<CardResponse>

    @POST("cards/{id}/excluir")
    suspend fun deleteCard(@Header("Authorization") token: String, @Path("id") id: String): Response<Unit>

    @POST("cards/{id}/concluir")
    suspend fun completeCard(@Header("Authorization") token: String, @Path("id") id: String): Response<Unit>

    @POST("cards/{id}/reativar")
    suspend fun reactivateCard(@Header("Authorization") token: String, @Path("id") id: String): Response<Unit>
}
