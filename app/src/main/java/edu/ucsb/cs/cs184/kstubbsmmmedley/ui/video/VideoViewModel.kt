package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.video

import android.net.Uri
import android.widget.VideoView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.ucsb.cs.cs184.kstubbsmmmedley.R

class VideoViewModel : ViewModel() {
    var currentPosition = MutableLiveData<Int>().apply {
        value = 0
    }
    var videoViewURI = MutableLiveData<String>()

    fun setVideoViewURI(string: String) {
        videoViewURI.value = string
    }
}