package fr.ephyles.heroscapearmycardlist

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import fr.ephyles.heroscapearmycardlist.Global.generalFilter

class GeneralDialogFragment(val callback: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Choose a general")
                .setItems(R.array.general_filter_options,
                    DialogInterface.OnClickListener { dialog, which ->
                        generalFilter = when(which) {
                            1 -> "Aquilla"
                            2 -> "Einar"
                            3 -> "Jandar"
                            4 -> "Ullar"
                            5 -> "Utgar"
                            6 -> "Valkrill"
                            7 -> "Vydar"
                            else -> {"All"}
                        }
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callback()
    }
}