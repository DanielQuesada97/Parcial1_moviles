package com.quesada.parquetracker.ui.park.parklist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quesada.parquetracker.data.model.ParkModel
import com.quesada.parquetracker.databinding.ParkItemBinding

class ParkRecyclerViewAdapter(
    private val clickListener: (ParkModel) -> Unit)
    : RecyclerView.Adapter<ParkRecyclerViewHolder>() {

    private val parks = ArrayList<ParkModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkRecyclerViewHolder {
        val binding = ParkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parks.size
    }

    override fun onBindViewHolder(holder: ParkRecyclerViewHolder, position: Int) {
        val park = parks[position]
        holder.bind(park, clickListener)
    }

    fun setData(parkList: List<ParkModel>) {
        parks.clear()
        parks.addAll(parkList)
    }
}