package com.atillak.todolist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atillak.todolist.R
import com.atillak.todolist.model.Task

class TaskAdapter(var context : Context, var taskList: ArrayList<Task>) :
        RecyclerView.Adapter<TaskAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item ,parent ,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = taskList[position].name
        holder.taskDate.text = taskList[position].date

    }

    fun updateList(newList: ArrayList<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = taskList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName = view.findViewById<TextView>(R.id.itemTaskName)
        val taskDate = view.findViewById<TextView>(R.id.itemDate)
        val completeCheckBox = view.findViewById<CheckBox>(R.id.itemComplete)

    }

}