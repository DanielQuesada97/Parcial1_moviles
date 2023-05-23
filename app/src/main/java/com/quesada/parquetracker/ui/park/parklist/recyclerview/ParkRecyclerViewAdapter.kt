package com.quesada.parquetracker.ui.park.parklist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quesada.parquetracker.data.model.ParkModel
import com.quesada.parquetracker.databinding.ParkItemBinding

class ParkRecyclerViewAdapter(
    private val clickListener: (ParkModel) -> Unit)
    : RecyclerView.Adapter<ParkRecyclerViewHolder>() {

    // ArrayList to store the park data
    private val parks = ArrayList<ParkModel>()

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkRecyclerViewHolder {
        // Inflate the layout for each item in the RecyclerView
        val binding = ParkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkRecyclerViewHolder(binding)
    }

    // Returns the total number of items in the data set
    override fun getItemCount(): Int {
        return parks.size
    }

    // Called to display the data at the specified position
    override fun onBindViewHolder(holder: ParkRecyclerViewHolder, position: Int) {
        val park = parks[position]
        // Bind the park data to the ViewHolder
        holder.bind(park, clickListener)
    }

    // Function to update the data set with a new list of parks
    fun setData(parkList: List<ParkModel>) {
        // Clear the existing park data
        parks.clear()
        // Add all the parks from the new list
        parks.addAll(parkList)
    }
}