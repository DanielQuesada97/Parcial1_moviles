package com.quesada.parquetracker.repositories

import com.quesada.parquetracker.data.model.ParkModel

class ParkRepository(private val parks: MutableList<ParkModel>) {
    fun getParks() = parks

    fun addParks(park: ParkModel) = parks.add(park)
}