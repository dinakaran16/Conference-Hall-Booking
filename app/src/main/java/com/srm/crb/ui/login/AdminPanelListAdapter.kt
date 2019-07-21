package com.srm.crb.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.srm.crb.R
import com.srm.crb.db.User

class AdminPanelListAdapter(var items: List<User>, val loginViewModel: LoginViewModel) : RecyclerView.Adapter<AdminPanelListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_component, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameView.text = item.userName

        holder.deleteView.setOnClickListener {
            loginViewModel.removeUser(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView = view.findViewById<TextView>(R.id.user_name)
        val deleteView = view.findViewById<ImageView>(R.id.iv_remove_user)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun changeItems(newElements: List<User>) {
        items = newElements
        notifyDataSetChanged()
    }
}