package com.capstone.nusal.data

data class CategoryModel(
    val categoryName: String,
    val categoryImage: String
)

data class KamusDetailModel(
    val wordName: String,
    val wordImageUrl: String,
    val wordDescription: String
    // and to be added, arti?, perbandingan?
)

data class LearnLanguageModel(
    val aksaraName: String,
    val aksaraImage: String
)