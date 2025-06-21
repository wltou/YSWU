package com.example.mediwithu

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.mediwithu.databinding.ActivityWriteAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class WriteAddActivity : AppCompatActivity() {

    lateinit var binding:ActivityWriteAddBinding
    lateinit var sharedPreference: SharedPreferences

    val today = Date()
    var selectedDate: Date = today
    val date_format = SimpleDateFormat("yyyy-MM-dd")
    val calendar = Calendar.getInstance().apply {
        time = today
    }

    val thisyear = calendar.get(Calendar.YEAR)
    val thismonth = calendar.get(Calendar.MONTH)
    val tnisday = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWriteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        
        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너") ?: "복약 파트너"

        val items = arrayOf(partner_idPre)

        binding.inputPartner.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("복약 파트너를 선택하세요")
                .setSingleChoiceItems(items, 1, object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int){
                        binding.inputPartner.text = items[p1]
                    }
            }).show()
        }

        binding.inputDate.setOnClickListener{
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view:DatePicker?, year: Int, month: Int, dayOfMonth: Int){
                    val cal = Calendar.getInstance()
                    cal.set(year, month, dayOfMonth)
                    selectedDate = cal.time
                    binding.inputDate.text = "$year 년 ${month + 1} 월 $dayOfMonth 일의 복약 일지"
                }
            }, thisyear, thismonth, tnisday).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너") ?: "복약 파트너"
        if(item.itemId == R.id.menu_add_save){
            if(binding.addEditView.text.isNotEmpty()){
                val data = mapOf(
                    "email" to MyApplication.email,
                    "partnerId" to partner_idPre,
                    "content" to binding.addEditView.text.toString(),
                    "date" to date_format.format(selectedDate))

                MyApplication.db.collection("diary")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "data save OK", Toast.LENGTH_LONG).show()
                        val helper = MyNotificationHelpher(this)
                        helper.showNotification("MediWithU", "복약 일지 등록 완료")
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "data save ERROR", Toast.LENGTH_LONG).show()
                    }

            }else{
                Toast.makeText(this, "입력 내용이 없습니다.", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}