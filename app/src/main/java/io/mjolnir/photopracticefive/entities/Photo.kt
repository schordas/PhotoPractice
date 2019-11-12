package io.mjolnir.photopracticefive.entities

object Photo {
    data class Result(val urls: URLs)
    data class URLs(val regular: String)
}