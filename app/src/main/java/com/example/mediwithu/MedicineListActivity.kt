package com.example.mediwithu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.mediwithu.databinding.ActivityAddBinding
import com.example.mediwithu.databinding.ActivityMainBinding
import com.example.mediwithu.databinding.ActivityMedicineListBinding

class MedicineListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMedicineListBinding
    lateinit var sharedPreference : SharedPreferences
    val datas = mutableListOf<MyItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicineListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너")
        binding.listTitle.text = "${partner_idPre} 복약 리스트"

        val morningLayout = findViewById<LinearLayout>(R.id.linear1)
        val lunchLayout = findViewById<LinearLayout>(R.id.linear2)
        val dinnerLayout = findViewById<LinearLayout>(R.id.linear3)

        val db = DBHelper(this).readableDatabase

        val cursor = db.rawQuery("select * from TOTAKE_TB", null)
        cursor.run{
            while(moveToNext()){
                val totake = getString(getColumnIndexOrThrow("totake"))
                val time = getString(getColumnIndexOrThrow("time"))

                datas.add(MyItem(totake, time))

                val tv = TextView(this@MedicineListActivity) // 또는 Fragment면 requireContext()
                tv.text = totake
                tv.textSize = 16f
                tv.setPadding(10, 10, 10, 10)

                when(time) {
                    "아침" -> morningLayout.addView(tv)
                    "점심" -> lunchLayout.addView(tv)
                    "저녁" -> dinnerLayout.addView(tv)
                    else -> {} // 기타 처리
                }
            }
        }
        db.close()


        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val totake = it.data?.getStringExtra("result")
            val time = it.data?.getStringExtra("time") ?: "None"
            if (!totake.isNullOrEmpty()) {
                datas.add(MyItem(totake, time))  // 데이터 리스트에 추가

                // 새 TextView 만들어서 추가
                val tv = TextView(this)
                tv.text = totake
                tv.textSize = 16f
                tv.setPadding(10, 10, 10, 10)

                when (time) {
                    "아침" -> morningLayout.addView(tv)
                    "점심" -> lunchLayout.addView(tv)
                    "저녁" -> dinnerLayout.addView(tv)
                    else -> Toast.makeText(this, "시간 오류", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.listFab.setOnClickListener{
            val intent = Intent(it.context, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

    }
}