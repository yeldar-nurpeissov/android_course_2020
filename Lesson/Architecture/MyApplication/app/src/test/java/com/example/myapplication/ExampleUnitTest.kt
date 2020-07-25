package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.forEach {
            print("$it")
        }

        val list2 = listOf(5, 6)

        list.addAll(list2)

        assertEquals(4, 2 + 2)
    }
}