package com.example.applicationcontentprovider.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationcontentprovider.NoteClickedListener
import com.example.applicationcontentprovider.R
import com.example.applicationcontentprovider.database.NotesDatabaseHelper.Companion.DESCRIPTION_NOTES
import com.example.applicationcontentprovider.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import kotlinx.android.synthetic.main.note_item.view.*
import org.w3c.dom.Text

class NotesAdapter( private val listener: NoteClickedListener ):RecyclerView.Adapter<NotesViewHolder>() {

    private var mCursor: Cursor ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
        NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))


    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        mCursor?.moveToPosition(position)

        holder.noteTitle.text = mCursor?.getString(mCursor?.getColumnIndex(TITLE_NOTES) as Int)
        holder.noteDescription.text = mCursor?.getString(mCursor?.getColumnIndex(DESCRIPTION_NOTES) as Int)

        holder.noteButtonRemove.setOnClickListener {
            mCursor?.moveToPosition(position)
            listener.noteRemoveItem(mCursor as Cursor)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener { listener.noteClickedItem(mCursor as Cursor) }
    }

    override fun getItemCount(): Int = if (mCursor != null) mCursor?.count as Int else 0


    fun setCursor(newCursor: Cursor?){
        mCursor = newCursor
        notifyDataSetChanged()
    }

}

class NotesViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    val noteTitle = itemView.notes_title as TextView
    val noteDescription = itemView.notes_description as TextView
    val noteButtonRemove = itemView.button_notes_remove as Button

}