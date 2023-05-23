package com.quesada.parquetracker.ui.park

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.quesada.parquetracker.R
import com.quesada.parquetracker.databinding.FragmentParkBinding
import com.quesada.parquetracker.ui.park.parklist.recyclerview.ParkRecyclerViewAdapter
import com.quesada.parquetracker.ui.park.viewmodel.ParkViewModel

class ParkFragment : Fragment() {
    private lateinit var adapter: ParkRecyclerViewAdapter
    private val parkViewModel: ParkViewModel by activityViewModels {
        ParkViewModel.Factory
    }
    private lateinit var binding: FragmentParkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
    }

    private fun setViewModel() {
        binding.viewmodel = parkViewModel
    }
}