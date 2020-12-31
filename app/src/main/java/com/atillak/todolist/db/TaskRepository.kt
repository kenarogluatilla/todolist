package com.atillak.todolist.db

import android.content.Context
import android.database.Cursor
import com.atillak.todolist.model.Task

class TaskRepository(var context : Context) {

    private var mDBHelper : DBHelper= DBHelper.getInstance(context)
    //private var mDBHelper = DBHelper.getInstance(context)


    fun getAllTask():ArrayList<Task> {
        val list = ArrayList<Task>()
        val db = mDBHelper.readableDatabase

        val query =
                "SELECT ${DBHelper.KEY_ID},${DBHelper.KEY_NAME}, ${DBHelper.KEY_DATE} FROM ${DBHelper.TABLE_NAME}"//BÜTÜN VERİLERİ ELDE ETTİĞİMİZ SORGU.

        var cursor : Cursor = db.rawQuery(query, null)// bu sınıf sayesinde veritabanındaki bütün kolonlar okunur.
        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID))
                val name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                val date = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DATE))

                val task = Task(id,name,date)
                list.add(task)
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return list
    }

    fun insertTask() {

    }

    fun deleteTask(){

    }

    fun updateTask() {

    }
}