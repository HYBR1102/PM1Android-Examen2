package com.example.examen2

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EliminarTareaDialogFragment(private val position: Int): DialogFragment() {

    // Use this instance of the interface to deliver action events
    internal lateinit var listener: EliminarTareaDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle("¿Eliminar tarea?")
            builder.setMessage("${Singleton.dataSet.get(position).titulo}")
                .setPositiveButton("Sí",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                        listener.onDialogPositiveClick(position)
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        listener.onDialogNegativeClick(position)
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /* The activity that creates an instance of this dialog fragment must
   * implement this interface in order to receive event callbacks.
   * Each method passes the DialogFragment in case the host needs to query it. */
    interface EliminarTareaDialogListener{
        fun onDialogPositiveClick(position: Int)
        fun onDialogNegativeClick(position: Int)
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as EliminarTareaDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement EliminarTareaDialogListener"))
        }
    }
}
