package com.quesada.parquetracker.ui.park.parklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quesada.parquetracker.R
import com.quesada.parquetracker.databinding.FragmentParkListBinding
import com.quesada.parquetracker.ui.park.parklist.recyclerview.ParkRecyclerViewAdapter
import com.quesada.parquetracker.ui.park.viewmodel.ParkViewModel

class ParkListFragment : Fragment() {

    private lateinit var adapter: ParkRecyclerViewAdapter
    private lateinit var binding: FragmentParkListBinding

    // Obtain the shared instance of ParkViewModel using activityViewModels delegate
    private val parkViewModel: ParkViewModel by activityViewModels {
        ParkViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView and click listener
        setRecyclerView(view)

        // Clear data in GameViewModel and navigate to NewGameFragment on FloatingActionButton click
        binding.floatBotton.setOnClickListener {
            parkViewModel.clearData()
            it.findNavController().navigate(R.id.action_gameListFragment_to_newGameFragment)
        }
    }

    private fun setRecyclerView(view: View) {
        // Set the layout manager for the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        // Initialize the adapter with a click listener lambda expression
        adapter = GameRecyclerViewAdapter { selectedGame ->
            showSelectedItem(selectedGame)
        }

        // Set the adapter for the RecyclerView
        binding.recyclerView.adapter = adapter

        // Display the games in the RecyclerView
        displayGames()
    }

    private fun showSelectedItem(game: GameModel) {
        // Set the selected game in the gameViewModel and navigate to gameFragment
        gameViewModel.setSelectedGame(game)
        findNavController().navigate(R.id.action_gameListFragment_to_gameFragment)
    }

    private fun displayGames() {
        // Update the adapter's game data with the games from GameViewModel and notify the adapter
        adapter.setData(gameViewModel.getGames())
        adapter.notifyDataSetChanged()
    }
}