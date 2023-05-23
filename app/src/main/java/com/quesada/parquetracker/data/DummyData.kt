package com.quesada.parquetracker.data

import com.quesada.parquetracker.data.model.ParkModel

val name = "Location 1"
val location = "This is the location of Park 1"
val description = "This is the description of Park 1, it's a nice park"

val parks = mutableListOf(
    ParkModel(name, location, description)
)