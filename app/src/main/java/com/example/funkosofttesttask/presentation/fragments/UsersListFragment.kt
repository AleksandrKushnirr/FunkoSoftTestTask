package com.example.funkosofttesttask.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.funkosofttesttask.R
import com.example.funkosofttesttask.presentation.UsersAdapter
import com.example.funkosofttesttask.presentation.viewmodel.UsersListViewModel

class UsersListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersAdapter

    private lateinit var viewModel: UsersListViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.users_list_fragment, container, false)
        val progressFrame = view.findViewById<FrameLayout>(R.id.progress_frame)

        viewModel = ViewModelProvider(requireActivity()).get(UsersListViewModel::class.java)
        viewModel.usersListLiveData.observe(viewLifecycleOwner, {
            adapter.setItems(it)
            progressFrame.visibility = View.GONE
        })

        initRecycler(view)

        return view
    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_list_users)
        adapter = context?.let { UsersAdapter(it, object : UsersAdapter.OnUserSelectListener{
            override fun onUserSelect(id: Int) {

                viewModel.onUserSelect(id)

                activity?.supportFragmentManager?.commit {
                    replace(R.id.container, UserInfoFragment())
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    addToBackStack(null)
                }

            }
        } )
        } !!
        recyclerView.adapter = adapter
    }
}