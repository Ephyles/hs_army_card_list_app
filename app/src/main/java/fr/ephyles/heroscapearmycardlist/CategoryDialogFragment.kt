package fr.ephyles.heroscapearmycardlist

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import fr.ephyles.heroscapearmycardlist.Global.*

class CategoryDialogFragment(val callback: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            builder.setTitle("Filter categories")
                .setMultiChoiceItems(R.array.category_filter_options, booleanArrayOf(official, c3v, sov)
                ) { dialog, which, isChecked ->
                    if (isChecked) {
                        when (which) {
                            0 -> official = true
                            1 -> c3v = true
                            2 -> sov = true
                        }
                    } else {
                        when (which) {
                            0 -> official = false
                            1 -> c3v = false
                            2 -> sov = false
                        }
                    }
                }
                .setPositiveButton("OK") { _, _ ->
                    dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callback()
    }
}