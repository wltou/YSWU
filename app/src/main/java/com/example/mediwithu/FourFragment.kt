package com.example.mediwithu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mediwithu.databinding.FragmentFourBinding
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FourFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFourBinding

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
        binding = FragmentFourBinding.inflate(inflater, container, false)
        binding.writeAddFab.setOnClickListener{
            if(MyApplication.checkAuth()){
                //AddActivity 호출
                val intent = Intent(requireContext(), WriteAddActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "인증을 먼저 해주세요.", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        if(MyApplication.checkAuth() || MyApplication.email != null){

            binding.diaryRecyclerView.visibility = View.VISIBLE

            MyApplication.db.collection("diary")
                .get()
                .addOnSuccessListener { result ->
                    var itemList = mutableListOf<ItemData>()
                    for(document in result){
                        val item = document.toObject(ItemData::class.java)
                        item.docId = document.id
                        itemList.add(item)
                    }
                    binding.diaryRecyclerView.layoutManager = LinearLayoutManager(context)
                    binding.diaryRecyclerView.adapter = MyDiaryAdapter(itemList)
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Firebase로부터 데이터 획득에 실패했습니다.", Toast.LENGTH_LONG).show()
                }
        }
        else{
            binding.diaryRecyclerView.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()

        if(MyApplication.checkAuth() || MyApplication.email != null){

            binding.diaryRecyclerView.visibility = View.VISIBLE

            MyApplication.db.collection("diary")
                .get()
                .addOnSuccessListener { result ->
                    var itemList = mutableListOf<ItemData>()
                    for(document in result){
                        val item = document.toObject(ItemData::class.java)
                        item.docId = document.id
                        itemList.add(item)
                    }
                    binding.diaryRecyclerView.layoutManager = LinearLayoutManager(context)
                    binding.diaryRecyclerView.adapter = MyDiaryAdapter(itemList)
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Firebase로부터 데이터 획득에 실패했습니다.", Toast.LENGTH_LONG).show()
                }
        }
        else{
            binding.diaryRecyclerView.visibility = View.GONE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FourFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FourFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}