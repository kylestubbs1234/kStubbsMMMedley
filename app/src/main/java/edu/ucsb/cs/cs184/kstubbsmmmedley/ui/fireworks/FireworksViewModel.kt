package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.fireworks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FireworksViewModel : ViewModel() {

    val xCoordsMovement = MutableList<Int>(10, {Random.nextInt(-10,10)})
    val yCoordsMovement = MutableList<Int>(10, {Random.nextInt(-10,10)})

    val recentXCoords = MutableList<Int>(10, {Random.nextInt(-10,10)})
    val recentYCoords = MutableList<Int>(10, {Random.nextInt(-10,10)})

    val startingX = MutableLiveData<Int>()
    val startingY = MutableLiveData<Int>()


    fun getRecentXCoord(i: Int): Int {
        return recentXCoords[i]
    }

    fun getRecentYCoord(i: Int): Int {
        return recentYCoords[i]
    }

    fun setRecentXCoord(i: Int, value: Int) {
        recentXCoords.set(i, value)
    }

    fun setRecentYCoord(i: Int, value: Int) {
        recentYCoords.set(i, value)
    }

    fun resetCoordsMovement() {
        for (i in 0..9) {
            xCoordsMovement[i] = Random.nextInt(-10,10)
            yCoordsMovement[i] = Random.nextInt(-10,10)
        }
    }

    fun FireworksViewModel() {
        val anim_thread = AnimationThread({this.drawFireworks()}, 50)
        anim_thread.start()
    }

    fun drawFireworks() {
        for (i in 0..9) {
            recentXCoords.set(i, recentXCoords[i] + xCoordsMovement[i])
            recentYCoords.set(i, recentYCoords[i] + yCoordsMovement[i])
        }
    }
}