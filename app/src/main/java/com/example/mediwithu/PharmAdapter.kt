package com.example.mediwithu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediwithu.databinding.ItemPharmBinding

class PharmAdapter(private val pharmacyList: MutableList<PharmItem>) :

    RecyclerView.Adapter<PharmAdapter.PharmViewHolder>() {

        inner class PharmViewHolder(val binding: ItemPharmBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: PharmItem) {
                binding.tvPharmacyName.text = item.institutionName ?: "이름 없음"
                binding.tvPharmacyAddress.text = item.address ?: "주소 없음"
                binding.tvPharmacyPhone.text = item.phoneNumber ?: "전화번호 없음"
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmViewHolder {
        val binding = ItemPharmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PharmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PharmViewHolder, position: Int) {
        holder.bind(pharmacyList[position])
    }

    override fun getItemCount(): Int = pharmacyList.size

}