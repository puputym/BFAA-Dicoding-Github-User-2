package com.puput.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.puput.github.databinding.ActivityMainBinding
import com.puput.github.model.UserList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter = UserAdapter()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelMain::class.java)


        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.setHasFixedSize(true)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchUser(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length!! > 0) searchUser(newText.toString())
                return false
            }
        })

        binding.tvBahasa.setOnClickListener(this)
    }

    private fun searchUser(username: String) {

        binding.progressBar.visibility = View.VISIBLE
        viewModel.setSearchData(username)
        viewModel.getSearchData().observe(this) {
            userAdapter.setDataList(it)
            binding.apply {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = userAdapter
                userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: UserList) {
                        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                        intent.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                        startActivity(intent)
                    }

                })
            }
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_bahasa->{
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
    }


}

