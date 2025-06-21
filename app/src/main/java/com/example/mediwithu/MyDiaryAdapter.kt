package com.example.mediwithu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.mediwithu.databinding.ItemDiaryBinding

class MyDiaryViewHolder(val binding: ItemDiaryBinding) : RecyclerView.ViewHolder(binding.root)
class MyDiaryAdapter(val datas: MutableList<ItemData>): RecyclerView.Adapter<MyDiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDiaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MyDiaryViewHolder(ItemDiaryBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MyDiaryViewHolder, position: Int) {
        val binding = (holder as MyDiaryViewHolder).binding

        val model = datas!![position]

        binding.itemPartner.text = model.partnerId
        binding.itemText.text = model.content
        binding.itemDate.text = model.date
    }
}