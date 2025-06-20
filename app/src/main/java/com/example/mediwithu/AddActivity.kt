package com.example.mediwithu

import android.app.Activity
import android.os.Bundle
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
        val date = intent.getStringExtra("today")
        binding.date.text = date

        binding.btnSave.setOnClickListener{
            val totake_str = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL("insert into TOTAKE_TB (totake) values (?)",  arrayOf<String>(totake_str))
            db.close()

            val intent = intent
            intent.putExtra("result", totake_str)
            setResult(Activity.RESULT_OK, intent)

            finish()
            true
        }
    }
}