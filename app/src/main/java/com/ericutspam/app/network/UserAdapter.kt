package com.ericutspam.app.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ericutspam.app.R

class UserAdapter(private val userList: List<itemViewModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userNameTextView)
        val userNIM: TextView = itemView.findViewById(R.id.userNIMTextView)
        // Add other views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        val nama = user.nama.toString()
        val nim = user.nim.toString()
        holder.userName.text = "Nama: $nama"
        holder.userNIM.text = "NIM: $nim"
        // Bind other user data to the views
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
