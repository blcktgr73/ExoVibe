package com.blacktiger.exovibe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blacktiger.exovibe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val recentVideos = mutableListOf<Uri>() // Placeholder for recent videos
    private lateinit var adapter: RecentVideosAdapter

    private val pickVideoLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            // Persist permission
            contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("videoUri", it)
            startActivity(intent)
            recentVideos.add(0, it)
            adapter.notifyDataSetChanged()
            saveRecentVideos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecentVideos()
        binding.buttonPickVideo.setOnClickListener {
            pickVideoLauncher.launch(arrayOf("video/*"))
        }

        // Setup RecyclerView for recent videos
        binding.recyclerViewRecent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RecentVideosAdapter(recentVideos) { uri ->
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("videoUri", uri)
            startActivity(intent)
        }
        binding.recyclerViewRecent.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun saveRecentVideos() {
        val prefs = getSharedPreferences("recent_videos", MODE_PRIVATE)
        val uris = recentVideos.map { it.toString() }.toSet()
        prefs.edit().putStringSet("uris", uris).apply()
    }

    private fun loadRecentVideos() {
        val prefs = getSharedPreferences("recent_videos", MODE_PRIVATE)
        val uris = prefs.getStringSet("uris", emptySet()) ?: emptySet()
        recentVideos.clear()
        recentVideos.addAll(uris.map { Uri.parse(it) })
    }
}