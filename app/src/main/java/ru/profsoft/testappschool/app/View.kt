package ru.profsoft.testappschool.app

import android.view.View
import androidx.core.view.isVisible

fun View.visible() {
    if (this.isVisible) this.visibility = View.GONE
    else this.visibility = View.VISIBLE
}