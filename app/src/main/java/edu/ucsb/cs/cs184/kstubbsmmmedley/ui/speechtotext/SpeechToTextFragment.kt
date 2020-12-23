package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.speechtotext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import edu.ucsb.cs.cs184.kstubbsmmmedley.R
import kotlinx.android.synthetic.main.fragment_speech_to_text.*
import org.w3c.dom.Text
import java.util.*


class SpeechToTextFragment : Fragment() {

    private lateinit var viewModel: SpeechToTextViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speech_to_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView = requireActivity().findViewById(R.id.speechtotextBox)

        viewModel = ViewModelProviders.of(this).get(SpeechToTextViewModel::class.java)

        viewModel.speechResult.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            str ->
                textView.text = str.toString()
        })

        val intent = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "text")
        speechtotextFab.setOnClickListener {
            try {
                startActivityForResult(intent, targetRequestCode)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            targetRequestCode -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    var list : ArrayList<String> = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                    var spokenText : String = list.get(0)
                    viewModel.speechResult.value = spokenText
                }
            }
        }
    }
}