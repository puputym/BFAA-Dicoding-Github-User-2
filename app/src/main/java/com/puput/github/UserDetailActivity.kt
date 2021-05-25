package com.puput.github


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.puput.github.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserDetailBinding

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        private val TAB_TITLES = intArrayOf(
            R.string. tablayout_1,
            R.string.tablayout_2
        )
    }

    private val viewModel: DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, data)
        detailUser(data.toString())

        binding.tvName.setOnClickListener(this)

        val setViewPager = SectionPagerAdapter(this)

        val viewPager: ViewPager2 = this.binding.viewPager
        viewPager.adapter = setViewPager
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            this.run {
                tab.text = resources.getString(TAB_TITLES[position])
            }
        }.attach()
    }

    private fun detailUser(username: String) {
        viewModel.setDetailUser(username)
        viewModel.getUserDetail().observe(this) {
            binding.apply {
                tvName.text = it.name
                tvUsername.text = it.login
                tvCompany.text = it.company
                tvLocation.text = it.location
                tvFollowers.text = it.followers
                tvFollowings.text = it.following
                tvRepository.text = it.public_repos
                Glide.with(applicationContext)
                    .load(it.avatar_url)
                    .apply(RequestOptions().override(80, 80))
                    .into(binding.tvAvatar)
            }

        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_name->finish()
        }
    }
}


