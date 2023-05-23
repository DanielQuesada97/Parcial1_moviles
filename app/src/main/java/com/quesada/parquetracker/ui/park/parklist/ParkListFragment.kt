package com.quesada.parquetracker.ui.park.parklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quesada.parquetracker.R
import com.quesada.parquetracker.data.model.ParkModel
import com.quesada.parquetracker.databinding.FragmentParkListBinding
import com.quesada.parquetracker.ui.park.parklist.recyclerview.ParkRecyclerViewAdapter
import com.quesada.parquetracker.ui.park.viewmodel.ParkViewModel

class ParkListFragment : Fragment() {

    private lateinit var adapter: ParkRecyclerViewAdapter
    private lateinit var binding: FragmentParkListBinding

    private val parkViewModel: ParkViewModel by activityViewModels {
        ParkViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParkListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(view)

        binding.floatBotton.setOnClickListener {
            parkViewModel.clearData()
            it.findNavController().navigate(R.id.action_parkListFragment_to_newParkFragment)
        }
    }

    private fun setRecyclerView(view: View) {
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = ParkRecyclerViewAdapter { selectedPark ->
            showSelectedItem(selectedPark)
        }

        binding.recyclerView.adapter = adapter

        displayParks()
    }

    private fun showSelectedItem(park: ParkModel) {
        parkViewModel.setSelectedPark(park)
        findNavController().navigate(R.id.action_parkListFragment_to_parkFragment)
    }

    private fun displayParks() {
        adapter.setData(parkViewModel.getParks())
        adapter.notifyDataSetChanged()
    }
}