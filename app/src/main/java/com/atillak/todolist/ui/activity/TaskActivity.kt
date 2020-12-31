package com.atillak.todolist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atillak.todolist.R

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(findViewById(R.id.task_toolbar))
        supportActionBar?.title = "App TODO"

    }
}