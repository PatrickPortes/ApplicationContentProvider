package com.example.applicationcontentprovider

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns._ID
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationcontentprovider.adapter.NotesAdapter
import com.example.applicationcontentprovider.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import com.example.applicationcontentprovider.database.NotesProvider.Companion.NOTES_BY_ID
import com.example.applicationcontentprovider.database.NotesProvider.Companion.URI_NOTES
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var noteRecyclerView: RecyclerView
    lateinit var noteAdd: FloatingActionButton

    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteAdd = floatingActionButtonAddNotes
        noteAdd.setOnClickListener{ }

        adapter = NotesAdapter( object : NoteClickedListener {

            @SuppressLint("Range")
            override fun noteClickedItem(cursor: Cursor) {
                val id: Long = cursor.getLong(cursor.getColumnIndex(_ID))
            }

            @SuppressLint("Range")
            override fun noteRemoveItem(cursor: Cursor) {
                val id: Long? = cursor?.getLong(cursor.getColumnIndex(_ID))
                contentResolver.delete(Uri.withAppendedPath(URI_NOTES, id.toString()), null, null)
            }
        })
        adapter.setHasStableIds(true)

        noteRecyclerView = recyclerViewNotes
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
        noteRecyclerView.adapter = adapter
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(this, URI_NOTES, null, null, null, TITLE_NOTES)

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if( data != null ){    }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }
}