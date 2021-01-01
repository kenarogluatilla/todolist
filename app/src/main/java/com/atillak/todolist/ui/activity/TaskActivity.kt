package com.atillak.todolist.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.atillak.todolist.R
import com.atillak.todolist.db.TaskRepository
import com.atillak.todolist.model.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TaskActivity : AppCompatActivity() {

    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(findViewById(R.id.task_toolbar))
        supportActionBar?.title = "App TODO"

        val confirm_fab = findViewById<FloatingActionButton>(R.id.confirm_fab)
        val task_name_edt = findViewById<EditText>(R.id.task_name_edt)
        val end_date_text = findViewById<TextView>(R.id.end_date_text)
        val end_date_layout = findViewById<LinearLayout>(R.id.end_date_layout)

        taskRepository = TaskRepository(this)

        confirm_fab.setOnClickListener {
            //boştan farklıysa işlemler gerçekleşsin.
            if (!TextUtils.isEmpty(task_name_edt.text.toString())){
                val date : String =
                        if (end_date_text.text == null || end_date_text.text == getString(R.string.end_date))" No end date"
                        else end_date_text.toString()

                val rowId = taskRepository.insertTask(Task(name = task_name_edt.text.toString(), date = date))
                if (rowId > -1){
                    Toast.makeText(this,"Eklendi" , Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this , "Ekleme başarısız.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Task adı boş geçilemez", Toast.LENGTH_SHORT).show()
            }

             end_date_layout.setOnClickListener {
                getDatePickerDialog()
            }
        }
    }

    private fun getDatePickerDialog(){
        val end_date_text = findViewById<TextView>(R.id.end_date_text)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

       val dialog = DatePickerDialog(this,{view, year , month , dayOfMonth ->
           val endDate = "$dayOfMonth.$month.$year"
           end_date_text.text = endDate
       }, year , month, day)

        //bugünden önceki günleri inaktif etmek.
        dialog.datePicker.minDate = System.currentTimeMillis()
        dialog.show()

    }
}