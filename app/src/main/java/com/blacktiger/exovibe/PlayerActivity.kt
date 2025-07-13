package com.blacktiger.exovibe

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import android.widget.FrameLayout
import android.view.Gravity

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        val videoView = VideoView(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        videoView.layoutParams = params
        layout.addView(videoView)
        setContentView(layout)

        val videoUri = intent.getParcelableExtra<Uri>("videoUri")
        if (videoUri != null) {
            videoView.setVideoURI(videoUri)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.start()
        } else {
            finish() // No video, close activity
        }
    }
}
