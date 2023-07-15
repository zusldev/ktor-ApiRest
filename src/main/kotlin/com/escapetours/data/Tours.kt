package com.escapetours.data

import kotlinx.serialization.Serializable

@Serializable
data class Tours(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val rating: Double,
    val city: String,
)
