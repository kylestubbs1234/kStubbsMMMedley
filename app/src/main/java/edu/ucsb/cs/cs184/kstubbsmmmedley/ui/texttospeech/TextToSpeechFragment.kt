package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import edu.ucsb.cs.cs184.kstubbsmmmedley.R
import kotlinx.android.synthetic.main.fragment_text_to_speech.*
import org.w3c.dom.Text
import java.util.*

class TextToSpeechFragment : Fragment() {

    private lateinit var viewModel: TextToSpeechViewModel
    lateinit var textToSpeech: TextToSpeech
    lateinit var textInputLayout: TextInputLayout

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_to_speech, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextToSpeechViewModel::class.java)
        textInputLayout = requireActivity().findViewById(R.id.textInputLayout)

        textToSpeech = TextToSpeech(context,
            OnInitListener {
                if (it != TextToSpeech.ERROR)
                    textToSpeech.setLanguage(Locale.US)

            })

        texttospeechFab.setOnClickListener {
            if (textInputLayout.isNotEmpty()) {
                viewModel.textInput.value = textInputLayout.editText?.text.toString()
            }
            if (viewModel.textInput.value  != "")
                textToSpeech.speak(viewModel.textInput.value, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}