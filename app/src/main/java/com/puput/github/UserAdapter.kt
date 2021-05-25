package com.puput.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.puput.github.databinding.ItemRowUserBinding
import com.puput.github.model.UserList

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    var listUser = ArrayList<UserList>()
    private lateinit var onItemClickCallback : OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: UserList)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setDataList(data : ArrayList<UserList>){
        this.listUser = data
    }
    inner class UserViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserList){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.avatar_url)
                    .apply(RequestOptions().override(70, 70))
                    .into(tvAvatar)
                tvUsername.text = data.login
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}