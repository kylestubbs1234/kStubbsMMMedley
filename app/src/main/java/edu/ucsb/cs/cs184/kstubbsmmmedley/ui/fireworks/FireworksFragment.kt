package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.fireworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.ucsb.cs.cs184.kstubbsmmmedley.R

class FireworksFragment : Fragment() {

    private lateinit var fireworksViewModel: FireworksViewModel
    private lateinit var fireworksDrawView: FireworksDrawView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fireworksViewModel = ViewModelProvider(this).get(FireworksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_fireworks, container, false)
        fireworksDrawView = root.findViewById(R.id.fireworksDrawView)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val observer = Observer<FireworksDrawView> {
                fireworksDrawView.invalidate()
        }
        fireworksViewModel.startingX.observe(viewLifecycleOwner, Observer {
            x ->
                fireworksViewModel.startingX.value = x
        })

        fireworksViewModel.startingY.observe(viewLifecycleOwner, Observer {
                y ->
            fireworksViewModel.startingY.value = y
        })

        fireworksViewModel.FireworksViewModel()
    }

}