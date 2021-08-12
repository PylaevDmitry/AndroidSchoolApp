package ru.profsoft.testappschool.ui.auth

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ShareProfileDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        return builder.setTitle("Поделиться")
            .setMessage("Отправить в Viber" + "\n" + "Отправить в Telegram")
            .setPositiveButton("ВЕРНУТЬСЯ", null)
            .create()
    }
}
