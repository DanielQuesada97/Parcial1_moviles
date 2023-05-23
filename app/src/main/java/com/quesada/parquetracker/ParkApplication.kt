package com.quesada.parquetracker

import com.quesada.parquetracker.data.parks
import com.quesada.parquetracker.repositories.ParkRepository

class ParkApplication {
    val parkRepository: ParkRepository by lazy {
        ParkRepository(parks)
    }
}