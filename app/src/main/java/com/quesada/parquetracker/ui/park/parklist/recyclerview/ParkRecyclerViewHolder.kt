package com.quesada.parquetracker.ui.park.parklist.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.quesada.parquetracker.data.model.ParkModel
import com.quesada.parquetracker.databinding.ParkItemBinding

class ParkRecyclerViewHolder(private val binding: ParkItemBinding) : RecyclerView.ViewHolder(binding.root) {
    // Function to bind the park data to the ViewHolder
    fun bind(park: ParkModel, clickListener: (ParkModel) -> Unit) {
        // Set the title TextView with the park name
        binding.titleTextView.text = park.name
        // Set the score TextView with the park location
        binding.locationTextView.text = park.location

        // Set an onClickListener on the card view to trigger the clickListener callback
        binding.firstCardView.setOnClickListener {
            // Call the clickListener and pass the park object
            clickListener(park)
        }
    }
}