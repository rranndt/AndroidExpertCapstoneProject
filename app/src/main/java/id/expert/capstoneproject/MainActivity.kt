package id.expert.capstoneproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import id.expert.capstoneproject.core.ui.PagerAdapter
import id.expert.capstoneproject.databinding.ActivityMainBinding
import id.expert.capstoneproject.ui.movies.MoviesFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ArrayList<Fragment>()
        fragment.add(MoviesFragment())
        instantiateFragment(favoriteClassName)?.let { fragment.add(it) }

        val titles = ArrayList<String>()
        titles.add(getString(R.string.movies))
        titles.add(getString(R.string.favorite))

        val pagerAdapter = PagerAdapter(
            fragment,
            this
        )

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

    }

    private val favoriteClassName: String
        get() = "id.expert.capstoneproject.favorite.FavoriteFragment"

    private fun instantiateFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Timber.e(e.toString())
            null
        }
    }

    // Fix MemoryLeak/LibraryLeak for Android Q user
    override fun onBackPressed() {
        finishAfterTransition()
    }

}