package com.example.applicationcontentprovider.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID

class NotesDatabaseHelper(context: Context): SQLiteOpenHelper(context, "databaseNotes", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL( "CREATE TABLE $TABLE_NOTES (" +
                "$_ID INTEGER NOT NULL PRIMARY KEY," +
                "$TITLE_NOTES TEXT NOT NULL, " +
                "$DESCRIPTION_NOTES TEXT NOT NULL)"

        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    companion object{
        //Nome da Tabela:   (como boa prática de projeto SQLite apenas o nome da tabela deve ter letra maiúscula)
        const val TABLE_NOTES: String = "Notes"

        //Coluna:
        const val TITLE_NOTES:String = "title"

        //Notas:
        const val DESCRIPTION_NOTES:String = "description"
    }
}