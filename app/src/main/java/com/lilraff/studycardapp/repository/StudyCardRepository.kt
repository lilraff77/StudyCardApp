package com.lilraff.studycardapp.repository

import com.lilraff.studycardapp.model.*
import com.lilraff.studycardapp.network.StudyCardApi

class StudyCardRepository(private val api: StudyCardApi) {

    private fun formatToken(token: String): String {
        val cleanToken = token.replace("Bearer ", "").trim()
        return "Bearer $cleanToken"
    }

    suspend fun login(req: LoginRequest): Result<LoginResponse> = try {
        val response = api.login(req)
        if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
        else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun register(req: UserRequest): Result<Unit> = try {
        val response = api.register(req)
        if (response.isSuccessful) Result.success(Unit)
        else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun fetchCards(token: String): Result<List<Task>> = try {
        val response = api.getCards(formatToken(token))
        if (response.isSuccessful) Result.success(response.body() ?: emptyList())
        else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun createCard(token: String, task: Task): Result<Task> = try {
        val request = CardRequest(
            titulo = task.name ?: "",
            materia = task.theme ?: "",
            descricao = task.text ?: "",
            dataEntrega = task.endDate ?: ""
        )
        val response = api.createCard(formatToken(token), request)
        if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
        else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun completeCard(token: String, cardId: String): Result<Unit> = try {
        val response = api.completeCard(formatToken(token), cardId)
        if (response.isSuccessful) Result.success(Unit) else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun deleteCard(token: String, cardId: String): Result<Unit> = try {
        val response = api.deleteCard(formatToken(token), cardId)
        if (response.isSuccessful) Result.success(Unit) else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun reactivateCard(token: String, cardId: String): Result<Unit> = try {
        val response = api.reactivateCard(formatToken(token), cardId)
        if (response.isSuccessful) Result.success(Unit) else Result.failure(Exception("${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }
}
