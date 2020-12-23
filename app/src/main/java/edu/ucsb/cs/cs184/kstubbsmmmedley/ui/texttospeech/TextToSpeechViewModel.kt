package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.texttospeech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextToSpeechViewModel : ViewModel() {
    var textInput = MutableLiveData<String>().apply {
        value = ""
    }
}