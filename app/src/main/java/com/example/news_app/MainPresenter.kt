package com.example.news_app

import android.text.Editable

class MainPresenter {
    fun checkPasswordValidation(charList: Editable): Boolean{
        val passwordValues = listOf('1','2','3','4','5','6','7','8','9','0')
        charList.forEach { char ->
            if (!(char in passwordValues)){
                return false
            }
        }
        return true
    }
}