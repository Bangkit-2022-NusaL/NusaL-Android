package com.capstone.nusal.helper

fun specifyAksara(codeToCheck: Int, languageType: String): String {
    when(languageType) {
        "Jawa" -> {
            return when(codeToCheck) {
                0 -> "ba"
                1 -> "ca"
                2 -> "da"
                3 -> "dha"
                4 -> "ga"
                5 -> "ha"
                6 -> "ja"
                7 -> "ka"
                8 -> "la"
                9 -> "ma"
                10 -> "na"
                11 -> "nga"
                12 -> "nya"
                13 -> "pa"
                14 -> "ra"
                15 -> "sa"
                16 -> "ta"
                17 -> "tha"
                18 -> "wa"
                19 -> "ya"
                else -> "Wrong"
            }
        }
        "Sunda" -> {
            return when(codeToCheck) {
                0 -> "ba"
                1 -> "ca"
                2 -> "da"
                3 -> "ga"
                4 -> "ha"
                5 -> "ja"
                6 -> "ka"
                7 -> "la"
                8 -> "ma"
                9 -> "na"
                10 -> "nga"
                11 -> "nya"
                12 -> "pa"
                13 -> "ra"
                14 -> "sa"
                15 -> "ta"
                16 -> "wa"
                17 -> "ya"
                else -> "Wrong"
            }
        }
        else -> return "Gagal mengambil jenis bahasa"
    }
}