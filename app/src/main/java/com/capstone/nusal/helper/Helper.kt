package com.capstone.nusal.helper

fun specifyAksara(codeToCheck: Int): String {
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