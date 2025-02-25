package com.example.nikibbc

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator


class SectionFragment : Fragment(R.layout.fragment_section) {

    private var sectionName: String? = null
    private var newsItems: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the arguments passed to the fragment
        arguments?.let {
            sectionName = it.getString("section_name")
            newsItems = it.getStringArrayList("news_items") ?: emptyList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv: TextView = view.findViewById(R.id.sectionTextView)
        val listView: ListView = view.findViewById(R.id.newsListView)
        tv.text = arguments?.getString("section_name")
        // Set up the adapter for the ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, newsItems)
        listView.adapter = adapter
    }

    companion object {
        // Factory method to create a new instance of SectionFragment with a specific section name and list of news items
        fun newInstance(sectionName: String, newsItems: List<String>): SectionFragment {
            val fragment = SectionFragment()
            val args = Bundle()
            args.putString("section_name", sectionName) // Pass the section name as an argument
            args.putStringArrayList("news_items", ArrayList(newsItems)) // Pass the news items as a list
            fragment.arguments = args
            return fragment
        }
    }
}

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Define the number of tabs (3 tabs: Top Stories, Sports, Entertainment)
    override fun getItemCount(): Int = 3

    // Return the correct fragment for each position
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SectionFragment.newInstance(
                "Top Stories",
                listOf("Breaking News 1", "Breaking News 2", "Breaking News 3")
            )  // Tab 0: Top Stories
            1 -> SectionFragment.newInstance(
                "Sports",
                listOf("Football Match Result", "Basketball Update", "Tennis News")
            )  // Tab 1: Sports
            2 -> SectionFragment.newInstance(
                "Entertainment",
                listOf("Movie Premiere", "Celebrity Gossip", "Music Album Release")
            )  // Tab 2: Entertainment
            else -> throw IllegalStateException("Invalid position")
        }
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find views
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        // Set up the adapter for ViewPager2
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Set up TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Stories"
                1 -> "Sports"
                2 -> "Entertainment"
                else -> throw IllegalStateException("Invalid position")
            }
        }.attach()

        // Handle edge-to-edge content (apply system window insets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
