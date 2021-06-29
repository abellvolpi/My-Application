package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.Candidato
import com.example.myapplication.MyArrayList
import com.example.myapplication.utils.Utilidades.unaccent
import java.io.BufferedReader
import java.io.InputStreamReader

object Csv {
    private var candidates: List<Candidato>? = null

    fun fetchCandidates(context: Context): List<Candidato> {
        return candidates ?: arrayListOf<Candidato>().also { list ->
            val streamReader = InputStreamReader(context.assets.open("AppAcademy_Candidates.csv"))
            val bufferedReader = BufferedReader(streamReader)
            var row: List<String>

            bufferedReader.readLine()

            while (bufferedReader.ready()) {
                row = bufferedReader.readLine().split(';')
                list.add(
                    Candidato(
                        row[0],
                        row[1].lowercase(),
                        row[2].filter { it.isDigit() }.toInt(),
                        row[3].lowercase()
                    )
                )
            }

        }
    }
    fun procuraInstrutoriOS(list: MyArrayList<Candidato>): Candidato? {
        val instrutor = list.toList().filter {
            it.idade <= 31 && it.idade % 2 != 0 && it.vaga != "ios" && it.estado == "sc" && it.idade >= 20 && it.nome.split(
                " "
            ).get(1).first() == 'v'
        }

        if (instrutor.size > 1) {
            return null
        }
        return instrutor.firstOrNull()
    }

    // MyArrayList ---> ArrayList (using extension)
    // arrayList.filter

    fun procuraInstrutorAndroid(list: MyArrayList<Candidato>): Candidato? {
        val instrutorAndroid = list.toList().filter {
            it.estado == "sc" && it.idade <= 31 && it.vaga != "android" && it.idade % 2 != 0 && Utilidades.contarVogais(
                it.nome.unaccent()
            ) == 3 && it.nome.split(" ")[0].last() == 'o'
        }
        return instrutorAndroid.firstOrNull()
    }
}