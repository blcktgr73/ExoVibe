package com.blacktiger.exovibe

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.provider.OpenableColumns

class RecentVideosAdapter(
    private val videos: List<Uri>,
    private val onClick: (Uri) -> Unit
) : RecyclerView.Adapter<RecentVideosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.imageViewThumbnail)
        val fileName: TextView = view.findViewById(R.id.textViewFileName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = videos.size

    private fun getFileName(context: Context, uri: Uri): String {
        var name: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (index >= 0) {
                        name = it.getString(index)
                    }
                }
            }
        }
        if (name == null) {
            name = uri.lastPathSegment
        }
        return name ?: "Unknown"
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uri = videos[position]
        holder.thumbnail.setImageResource(R.drawable.ic_launcher_foreground)
        try {
            holder.fileName.text = getFileName(holder.itemView.context, uri)
        } catch (e: SecurityException) {
            holder.fileName.text = "Permission denied"
        }
        holder.itemView.setOnClickListener { onClick(uri) }
    }
}
