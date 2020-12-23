package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.video

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.ucsb.cs.cs184.kstubbsmmmedley.R
import kotlinx.android.synthetic.main.fragment_video.*
import kotlin.math.log


class VideoFragment : Fragment() {

    private lateinit var viewModel: VideoViewModel
    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        videoView = requireActivity().findViewById(R.id.videoView)

        viewModel.videoViewURI.observe(viewLifecycleOwner, Observer {
            it?.let { uri ->
                videoView.setVideoURI(uri.toUri())
            }
        })

        viewModel.setVideoViewURI("android.resource://" + activity?.packageName + "/" + R.raw.bigbuck)

        videoView.setMediaController(MediaController(context))

        if (videoView.currentPosition != -1)
            viewModel.currentPosition.value = videoView.currentPosition

        viewModel.currentPosition.value?.let { videoView.seekTo(it) }

        videoView.start()
    }

}