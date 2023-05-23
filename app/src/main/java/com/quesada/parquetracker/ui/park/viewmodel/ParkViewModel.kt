package com.quesada.parquetracker.ui.park.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.quesada.parquetracker.ParkApplication
import com.quesada.parquetracker.data.model.ParkModel
import com.quesada.parquetracker.repositories.ParkRepository

class ParkViewModel(private val repository: ParkRepository) : ViewModel() {

    // MutableLiveData fields to hold the input values for a park
    var name = MutableLiveData("")
    var location = MutableLiveData("")
    var description = MutableLiveData("")
    var status = MutableLiveData("")


    //Function to retrieve parks from repository
    fun getParks() = repository.getParks()

    //Function to add a park to the repository
    fun addParks(park: ParkModel) = repository.addParks(park)

    //Function to set the selected park's data to the input fields
    fun setSelectedPark(park: ParkModel) {
        name.value = park.name
        location.value = park.location
        description.value = park.description
    }

    //Function to validate the input data
    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            location.value.isNullOrEmpty() -> return false
            description.value.isNullOrEmpty() -> return false
        }
        return true
    }

    //Function to create a new park with the provided input data
    fun createPark(){
        //Validate input data before creating new park
        if(!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        // Create a new park object with the provided input values
        val park = ParkModel(
            name.value!!,
            location.value!!,
            description.value!!
        )
        //Add park to repository
        addParks(park)
        //Clear Input Fields
        clearData()
        //Set the status message indicating that the park was created
        status.value = PARK_CREATED
    }

    fun clearData() {
        name.value = ""
        location.value = ""
        description.value = ""
    }

    fun clearStatus() {
        status.value = INACTIVE
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ParkApplication
                ParkViewModel(app.parkRepository)
            }
        }
        const val PARK_CREATED = "Park Created"
        const val WRONG_INFORMATION = "Wrong Information"
        const val INACTIVE = "Inactive"
    }

}