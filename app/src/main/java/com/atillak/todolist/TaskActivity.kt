package com.atillak.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(findViewById(R.id.task_toolbar))
        supportActionBar?.title = "App TODO"

    }
}