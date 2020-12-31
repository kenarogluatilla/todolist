package com.atillak.todolist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atillak.todolist.R
import com.atillak.todolist.db.TaskRepository
import com.atillak.todolist.model.Task
import com.atillak.todolist.ui.adapter.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var taskRepository: TaskRepository
    private lateinit var taskList : ArrayList<Task>
    private lateinit var adapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))
        supportActionBar?.title = "TODO-List"

        taskRepository = TaskRepository(this)
        taskList = taskRepository.getAllTask()


        val recyclerView = findViewById<RecyclerView>(R.id.task_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(this, taskList)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}



