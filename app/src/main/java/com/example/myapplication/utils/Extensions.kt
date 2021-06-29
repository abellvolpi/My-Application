package com.example.myapplication.utils

import com.example.myapplication.PSList

inline fun <T : Any> PSList<T>.forEach(operation: (T) -> Unit): Unit {
    for (i in 0 until size()) operation(this[i])
}

fun <T : Any> PSList<T>.toList(): List<T> {
    val newArray = ArrayList<T>(size())
    for (i in 0 until size()) {
        newArray.add(this[i])
    }
    return newArray
}


