package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.imagescaling

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageScalingViewModel : ViewModel() {
    private val _scaleFactor = MutableLiveData<Float>().apply {
        value = 1.0f
    }
    fun getScaleFactor() : Float? {
        return _scaleFactor.value
    }
    fun setScaleFactor(float: Float) {
        _scaleFactor.value = float
    }

    val _viewModel = MutableLiveData<String>()

    fun bind() {
        _viewModel.value = "https://picsum.photos/400/600"
    }
}