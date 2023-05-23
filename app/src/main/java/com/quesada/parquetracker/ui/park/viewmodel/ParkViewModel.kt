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


    fun getParks() = repository.getParks()

    fun addParks(park: ParkModel) = repository.addParks(park)

    fun setSelectedPark(park: ParkModel) {
        name.value = park.name
        location.value = park.location
        description.value = park.description
    }

    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            location.value.isNullOrEmpty() -> return false
            description.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun createPark(){
        if(!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        val park = ParkModel(
            name.value!!,
            location.value!!,
            description.value!!
        )
        addParks(park)
        clearData()
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