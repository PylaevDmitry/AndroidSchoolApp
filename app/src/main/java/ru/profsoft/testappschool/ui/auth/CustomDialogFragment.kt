package ru.profsoft.testappschool.ui.auth

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        return builder.setTitle("Забыли пароль?")
            .setMessage("Напишите в поддержку academy@profsoft.pro")
            .setPositiveButton("ВЕРНУТЬСЯ", null)
            .create()
    }
}
