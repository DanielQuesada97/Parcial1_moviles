package com.quesada.parquetracker

import android.app.Application
import com.quesada.parquetracker.data.parks
import com.quesada.parquetracker.repositories.ParkRepository

class ParkApplication : Application() {
    val parkRepository: ParkRepository by lazy {
        ParkRepository(parks)
    }
}