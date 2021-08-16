package ru.profsoft.testappschool

import android.text.Editable

fun Editable?.formatIsCorrect(regex: Regex, count:Int):Boolean =
    (!regex.containsMatchIn(this.toString())) && (this!!.length>=count)

fun Editable?.formatIsCorrect():Boolean =
    this.toString().indexOf('@') != -1
            && this.toString().indexOf('@') == this.toString().lastIndexOf('@')

