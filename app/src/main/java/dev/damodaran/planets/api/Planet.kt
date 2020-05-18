package dev.damodaran.planets.api

data class Planet(
    val order: Int,
    val name: String,
    val description: String,
    val image: String,
    val url: String)