package com.example.mediwithu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mediwithu.databinding.FragmentTwoBinding
import com.example.mediwithu.databinding.ItemRecyclerviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private val sidoMap = mapOf(
        "서울특별시" to "110000",
        "부산광역시" to "260000",
        "대구광역시" to "270000",
        "인천광역시" to "280000",
        "광주광역시" to "290000",
        "대전광역시" to "300000",
        "울산광역시" to "310000",
        "세종특별자치시" to "360000",
        "경기도" to "410000",
        "강원도" to "420000",
        "충청북도" to "430000",
        "충청남도" to "440000",
        "전라북도" to "450000",
        "전라남도" to "460000",
        "경상북도" to "470000",
        "경상남도" to "480000",
        "제주특별자치도" to "500000"
    )

    private fun getSidoCode(sidoName: String): String? {
        return sidoMap[sidoName]
    }

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
        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        var hospitalfragment = HospitalFragment()
        var pharmfragment = PharmFragment()
        val bundle = Bundle()

        binding.btnSearch.setOnClickListener {
            val sidoInput = binding.edtSido.text.toString().trim()
            val sgguInput = binding.edtSggu.text.toString().trim()

            if (sidoInput.isEmpty()) {
                Toast.makeText(requireContext(), "시도는 필수로 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sidoCode = if (binding.rGroup.checkedRadioButtonId == R.id.rbHospital) {
                getSidoCode(sidoInput)
            } else {
                sidoInput
            }

            if (sidoCode == null) {
                Toast.makeText(requireContext(), "입력한 지역명이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            bundle.apply {
                putString("sidoCd", sidoCode)
                putString("sgguCd", sgguInput)
            }

            val targetFragment = if (binding.rGroup.checkedRadioButtonId == R.id.rbHospital) {
                HospitalFragment()
            } else {
                PharmFragment()
            }.apply {
                arguments = bundle
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_content, targetFragment)
                .commit()
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}