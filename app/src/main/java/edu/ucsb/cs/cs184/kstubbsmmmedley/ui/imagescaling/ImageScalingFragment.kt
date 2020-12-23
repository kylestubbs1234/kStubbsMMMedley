package edu.ucsb.cs.cs184.kstubbsmmmedley.ui.imagescaling

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import edu.ucsb.cs.cs184.kstubbsmmmedley.R
import kotlinx.android.synthetic.main.fragment_image_scaling.*

class ImageScalingFragment : Fragment(){

    private lateinit var viewModel: ImageScalingViewModel
    var scaleGestureDetector: ScaleGestureDetector? = null
    lateinit var imageViewScale : ImageView
    //var scaleFactor: Float = 1.0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_image_scaling, container, false)
        scaleGestureDetector = ScaleGestureDetector(context, MyOnScaleGestureListener())

        view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                scaleGestureDetector?.onTouchEvent(event)
                return true
            }
        });

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageViewScale = requireActivity().findViewById<ImageView>(R.id.imageScalingView)

        viewModel = ViewModelProviders.of(this).get(ImageScalingViewModel::class.java)
        viewModel._viewModel.observe(viewLifecycleOwner, Observer {
            it?.let { url ->
                Glide.with(this)
                    .load(url)
                    .error(R.drawable.default400by600pic)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageViewScale)
            }
        })

        viewModel.bind()

        imageScalingFab.setOnClickListener { view ->
            viewModel.bind()

        }
    }

    inner class MyOnScaleGestureListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {

            viewModel.getScaleFactor()?.times(detector.scaleFactor)?.let {
                viewModel.setScaleFactor(
                    it
                )
            }

            viewModel.getScaleFactor()?.let { imageViewScale.setScaleX(it) }
            viewModel.getScaleFactor()?.let { imageViewScale.setScaleY(it) }

            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }
    }
}