package com.example.funkosofttesttask.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funkosofttesttask.R
import com.example.funkosofttesttask.entity.User

class UsersAdapter(
    private val context: Context,
    private val listener: OnUserSelectListener
) : RecyclerView.Adapter<UsersAdapter.RemindsViewHolder>() {

    private var items: MutableList<User> = mutableListOf()

    fun setItems(reminders: List<User>) {
        items.clear()
        items.addAll(reminders)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RemindsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return RemindsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RemindsViewHolder, position: Int) {
        holder.bind(items[position], context)
        holder.itemView.setOnClickListener { listener.onUserSelect(items[position].id) }
    }

    inner class RemindsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){

        private val name = itemView.findViewById<TextView>(R.id.text_name)
        private val avatar = itemView.findViewById<ImageView>(R.id.image_user)


        @SuppressLint("SetTextI18n")
        fun bind(item: User, context: Context) {

            Glide.with(context)
                .load(item.image)
                .placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_error)
                .into(avatar)

            name.text = "${item.name} ${item.surname}"
        }
    }

    interface OnUserSelectListener{
        fun onUserSelect(id: Int)
    }

}