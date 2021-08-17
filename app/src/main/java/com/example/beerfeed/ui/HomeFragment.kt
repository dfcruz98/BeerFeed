package com.example.beerfeed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beerfeed.R
import com.example.beerfeed.databinding.FragmentHomeBinding
import com.example.beerfeed.ui.favorites.FavoritesFragment
import com.example.beerfeed.ui.feed.FeedFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_home,
            container,
            false
        )

        val tabItems = listOf(
            TabItem(getString(R.string.feed), FeedFragment.newInstance()),
            TabItem(getString(R.string.favorites), FavoritesFragment.newInstance())
        )

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return tabItems.size
            }

            override fun createFragment(position: Int): Fragment {
                return tabItems[position].fragment
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabItems[position].title
        }.attach()

        return binding.root
    }

    private data class TabItem(val title: String, val fragment: Fragment)
}