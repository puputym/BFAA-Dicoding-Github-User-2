package com.puput.github

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.puput.github.databinding.FragmentFollowersFollowingsBinding
import com.puput.github.model.UserList

class FragmentFollowings : Fragment(R.layout.fragment_followers_followings) {

    private lateinit var binding: FragmentFollowersFollowingsBinding

    private lateinit var adapter: UserAdapter
    private val viewModel: DetailUserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentFollowersFollowingsBinding.inflate(inflater, container, false)

        binding.apply {
            recyclerViewFollow.setHasFixedSize(true)
            recyclerViewFollow.layoutManager = LinearLayoutManager(activity)

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        binding.progressBarFollow.visibility = View.VISIBLE
        viewModel.following.observe(viewLifecycleOwner) {
            adapter.setDataList(it)
            binding.recyclerViewFollow.adapter = adapter
            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: UserList) {
                    val intent = Intent(context, UserDetailActivity::class.java)
                    intent.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                    startActivity(intent)
                }

            })
            binding.progressBarFollow.visibility = View.INVISIBLE
        }
    }
}



