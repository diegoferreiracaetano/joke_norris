package com.diegoferreiracaetano.chuckNorris.util

import android.graphics.Color

object ColorUtils {

    private val colors = arrayOf("039BE5", "0F9D58", "4285F4", "FF5722", "DB4437", "689F38", "009688", "DB4437", "3F51B5", "9C27B0", "4E342E", "F50057", "42A5F5", "009688", "9E9D24", "00C853", "BF360C", "37474F",
           "800000","808000","00FF00","008000","000000","008080","0000FF","000080","FF00FF","800080")


    fun parse(c:Char):Int{
        val array = 'A'..'Z'
        var key = 0

        array
            .forEachIndexed{ index, element ->
                if(element == c)
                    key = index
            }

        return Color.parseColor("#" + colors[key])
    }
}