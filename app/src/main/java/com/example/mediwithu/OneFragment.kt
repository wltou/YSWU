package com.example.mediwithu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mediwithu.databinding.ActivityMainBinding
import com.example.mediwithu.databinding.FragmentOneBinding
import java.text.SimpleDateFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentOneBinding
    lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false)


        var datas = mutableListOf<MyItem>()
        val db = DBHelper(inflater.context).readableDatabase

        val cursor = db.rawQuery("select * from TOTAKE_TB", null)
        cursor.run{
            while(moveToNext()){
                val totake = getString(getColumnIndexOrThrow("totake"))
                val time = getString(getColumnIndexOrThrow("time"))
                datas.add(MyItem(totake, time))
            }
        }
        db.close()

        sharedPreference= PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너")
        val partner_namePre = sharedPreference.getString("partner_name", "복약 파트너")
        val partner_telPre = sharedPreference.getString("partner_tel", "")
        val messagePre = sharedPreference.getString("default_message_pre", "$partner_idPre! 오늘 하루 잘 보내고 계신가요? 약 챙기실 시간이예요~") ?: "오늘 하루 잘 보내고 계신가요? 약 챙기실 시간이예요~"


        val radapter = MyAdapter(datas)

        binding.RecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.RecyclerView.adapter = radapter
        binding.RecyclerView.addItemDecoration(MyDecoration(activity as Context))
        binding.tvname.text = "${partner_idPre}"

        val requestLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                val totake = it.data?.getStringExtra("result")
                val time = it.data?.getStringExtra("time") ?: "None"
                if(!totake.isNullOrEmpty()){
                    datas.add(MyItem(totake, time))
                    radapter.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }
        binding.fab.setOnClickListener{
            val intent = Intent(it.context, AddActivity::class.java)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            intent.putExtra("today", dateFormat.format(System.currentTimeMillis()))
            requestLauncher.launch(intent)
        }

        binding.btnCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$partner_telPre")
            startActivity(intent)
        }
        binding.btnMessage.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$partner_telPre"))
            intent.putExtra("sms_body", messagePre)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val partner_idPre = sharedPreference.getString("partner_id", "복약 파트너")

        binding.tvname.text = "${partner_idPre}"

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}