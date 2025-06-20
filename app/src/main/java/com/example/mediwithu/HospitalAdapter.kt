package com.example.mediwithu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediwithu.databinding.ItemHospitalBinding

class HospitalAdapter(private val datas: MutableList<myHospitalItem>?) :
    RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    inner class HospitalViewHolder(val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: myHospitalItem) {
            binding.tvHospitalName.text = item.yadmNm ?: "이름 없음"
            binding.tvHospitalAddress.text = item.addr ?: "주소 없음"
            binding.tvHospitalPhone.text = item.telno ?: "전화번호 없음"
            binding.tvHoapitalWeb.text = item.hospUrl ?: "홈페이지 없음"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val item = datas?.get(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}
