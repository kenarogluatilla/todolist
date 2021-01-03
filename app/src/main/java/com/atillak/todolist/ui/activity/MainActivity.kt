package com.atillak.todolist.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atillak.todolist.R
import com.atillak.todolist.db.TaskRepository
import com.atillak.todolist.model.Task
import com.atillak.todolist.ui.adapter.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , TaskAdapter.OnTaskCompleteListener, TaskAdapter.OnTaskEditListener{

    private lateinit var taskRepository: TaskRepository
    private lateinit var taskList : ArrayList<Task>
    private lateinit var adapter : TaskAdapter

    companion object{
        const val EXTRA_TASK = "extra_task"
    }

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

        adapter.setOnTaskCompleteListener(this)
        adapter.setOnTaskEditListener(this)


        var add_task_fab = findViewById<FloatingActionButton>(R.id.add_task_fab)
        add_task_fab.setOnClickListener{startActivity(Intent(this, TaskActivity::class.java)) }

    }

    override fun onEditTask(task: Task) {
       val intent = Intent(this, TaskActivity::class.java)
        intent.putExtra(EXTRA_TASK, task)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        taskList = taskRepository.getAllTask()
        adapter.updateList(taskList)
    }

    override fun onTaskComplete(taskId: Int) {
        taskRepository.deleteTask(taskId)
        taskList=taskRepository.getAllTask()
        adapter.updateList(taskList)
    }
}



