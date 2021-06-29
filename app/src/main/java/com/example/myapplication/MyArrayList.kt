package com.example.myapplication

class MyArrayList<T : Any> : PSList<T> {

    private var dataArray: Array<Any?> = Array(DEFAULT_LIST_INIT_SIZE) {
        Any()
    }
    private var count = 0


    override fun contains(item: T): Boolean {
        dataArray.forEach {
            if (it == item) {
                return true
            }
        }
        return false
    }

    override fun get(position: Int): T {
        if (position < 0 || position > count ) {  // if( position !in 0 until conunt) {
            throw ArrayIndexOutOfBoundsException()
        }
        return dataArray[position] as T
    }

    override fun add(item: T) {
        if (count < dataArray.size) {
            dataArray[count++] = item
            return
        }
        dataArray = Array(dataArray.size + DEFAULT_LIST_INIT_SIZE) {
            if (it < dataArray.size) {
                dataArray[it]
            } else{
                null
            }
        }
        dataArray[count++] = item
    }

    override fun remove(position: Int): Boolean {
        if (position < 0 || position > count) {
            throw ArrayIndexOutOfBoundsException()
            return false
        }
        val available = dataArray.size - (--count)
        if (available > DEFAULT_LIST_INIT_SIZE) {
            val newSize = dataArray.size - DEFAULT_LIST_INIT_SIZE
            dataArray = Array(newSize) {
                when {
                    it < position -> dataArray[it]
                    it < count -> dataArray[it + 1]
                    else -> null
                }
            }
        } else {
            for (i in position..count) {
                dataArray[i] = when {
                    i + 1 >= dataArray.size -> null
                    else -> dataArray[i + 1]
                }
            }
        }
        return true
    }
    override fun size(): Int {
        return count
    }

    override fun realSize(): Int {
        return dataArray.size
    }
}





