package com.quesada.parquetracker.ui.park.newpark

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.quesada.parquetracker.R
import com.quesada.parquetracker.databinding.FragmentNewParkBinding
import com.quesada.parquetracker.ui.park.viewmodel.ParkViewModel

class NewParkFragment : Fragment() {

    private val parkViewModel: ParkViewModel by activityViewModels {
        ParkViewModel.Factory
    }

    private lateinit var nameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var submitButton: Button

    private lateinit var binding: FragmentNewParkBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()
        setViewModel()
        observeStatus()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewParkBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setViewModel() {
        binding.viewmodel = parkViewModel
    }

    private fun observeStatus() {
        parkViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(ParkViewModel.WRONG_INFORMATION) -> {
                    Log.d("tag", status)
                    parkViewModel.clearStatus()
                }
                status.equals(ParkViewModel.PARK_CREATED) -> {
                    Log.d("tag", status)
                    Log.d("tag", parkViewModel.getParks().toString())

                    parkViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun bind() {
        // Bind the views to their respective variables
        nameEditText = view?.findViewById(R.id.nameTextField2) as EditText
        locationEditText = view?.findViewById(R.id.locationTextField2) as EditText
        descriptionEditText = view?.findViewById(R.id.descriptionTextField2) as EditText
        submitButton = view?.findViewById(R.id.submitButton) as Button
    }

}