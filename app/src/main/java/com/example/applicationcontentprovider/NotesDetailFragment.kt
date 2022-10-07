package com.example.applicationcontentprovider

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.note_detail.*

class NotesDetailFragment:DialogFragment(), DialogInterface.OnClickListener {

    private lateinit var noteEditTitle: EditText
    private lateinit var noteEditDescription: EditText

    private var id: Long = 0

    companion object{

        private const val EXTRA_ID = "id"
        fun newInstance (id: Long): NotesDetailFragment {
            var bundle = Bundle()
            bundle.putLong(EXTRA_ID, id)

            val notesFragment = NotesDetailFragment()
            notesFragment.arguments = bundle
            return notesFragment
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.note_detail, null)

        noteEditTitle = edt_note_title
        noteEditDescription = edt_note_description

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        TODO("Not yet implemented")
    }
}