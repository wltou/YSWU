package com.example.mediwithu

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "yswudb", null, 3) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table TOTAKE_TB (" +
                "_id integer primary key autoincrement," +
                "totake TEXT not null," +
                "time TEXT not null)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS TOTAKE_TB")
        onCreate(db)
    }
}