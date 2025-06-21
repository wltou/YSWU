package com.example.mediwithu

import android.app.Activity
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mediwithu.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSave.setOnClickListener{
            val totake_str = binding.addEditView.text.toString()
            val timeType = when (binding.rTime.checkedRadioButtonId){
                R.id.rTime1 -> binding.rTime.findViewById<RadioButton>(R.id.rTime1).text.toString()
                R.id.rTime2 -> binding.rTime.findViewById<RadioButton>(R.id.rTime2).text.toString()
                R.id.rTime3 -> binding.rTime.findViewById<RadioButton>(R.id.rTime3).text.toString()
                else -> "None"
            }
            val db = DBHelper(this).writableDatabase
            db.execSQL("insert into TOTAKE_TB (totake, time) values (?, ?)",  arrayOf<String>(totake_str, timeType))
            db.close()

            val intent = intent
            intent.putExtra("result", totake_str)
            intent.putExtra("time", timeType)
            setResult(Activity.RESULT_OK, intent)

            finish()
            true
        }
    }
}