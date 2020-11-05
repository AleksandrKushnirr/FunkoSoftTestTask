package com.example.funkosofttesttask.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.funkosofttesttask.R
import com.example.funkosofttesttask.ui.viewmodel.UsersListViewModel

class UserInfoFragment : Fragment(){


    private lateinit var viewModel: UsersListViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.users_info_fragment, container, false)

        val avatar = view.findViewById<ImageView>(R.id.image_user)
        val name = view.findViewById<TextView>(R.id.text_name)
        val email = view.findViewById<TextView>(R.id.text_email)

        viewModel = ViewModelProvider(requireActivity()).get(UsersListViewModel::class.java)
        viewModel.userLiveData.observe(viewLifecycleOwner, {

            Glide.with(activity?.applicationContext!!)
                .load(it.image)
                .placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_error)
                .into(avatar)

            name.text = "${it.name} ${it.surname}"
            email.text = it.email

        } )
        return view
    }

}