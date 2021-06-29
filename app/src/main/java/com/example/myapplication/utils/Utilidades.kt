package com.example.myapplication.utils

import java.text.Normalizer
import java.util.regex.Pattern

object Utilidades {

    fun semAcento(str: String): String {
        var semacento: String = Normalizer.normalize(str, Normalizer.Form.NFD)
        var pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(semacento).replaceAll("")
    }

    fun contarVogais(nome: String): Int {
        var n_vogais: Int = 0
        nome.lowercase().split(" ")[0].forEach {
            when (it) {
                'a', 'e', 'i', 'o', 'u' -> n_vogais++
            }
        }
        return n_vogais
    }

    fun numeroPrimo(numero: Int): Boolean {
        for (i in 2 until numero) {
            if (numero % i == 0) {
                return false
            }
        }
        return true
    }

    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    fun String.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }

    fun getLogin(name: String): String {
        return name.replace(oldChar = ' ', newChar = '_').lowercase()
    }




}