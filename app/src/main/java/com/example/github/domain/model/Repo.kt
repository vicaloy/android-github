package com.example.github.domain.model

data class Repo(
    val id: Long,
    val name: String,
    val owner: Owner
)

data class Owner(
    val login: String,
    val id: Long,
)