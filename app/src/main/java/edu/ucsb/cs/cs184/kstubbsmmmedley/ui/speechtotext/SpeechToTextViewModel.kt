package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.speechtotext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpeechToTextViewModel : ViewModel() {
    var speechResult = MutableLiveData<String>()
}