package com.puput.github

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment?=null

        when(position){
            0->fragment = FragmentFollowers()
            1->fragment = FragmentFollowings()
        }
        return fragment as Fragment
    }
}