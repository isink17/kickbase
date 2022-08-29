package hr.isinkovic.kickbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hr.isinkovic.kickbase.api.MatchesResponse
import hr.isinkovic.kickbase.api.MatchesService
import hr.isinkovic.kickbase.data.MatchesRepository
import hr.isinkovic.kickbase.databinding.ActivityMainBinding
import hr.isinkovic.kickbase.ui.MatchesAdapter
import hr.isinkovic.kickbase.vm.MatchesViewModel
import hr.isinkovic.kickbase.vm.MatchesViewModelFactory

class MainActivity : AppCompatActivity(), Observer<MatchesResponse> {
    private lateinit var viewModel: MatchesViewModel
    private lateinit var binding: ActivityMainBinding
    private var mAdapter: MatchesAdapter = MatchesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.titleText.text = getString(R.string.lbl_screen_title)

        val matchesInstance = MatchesService.create()
        val repository = MatchesRepository(matchesInstance)

        viewModel = ViewModelProvider(
            this,
            MatchesViewModelFactory(repository)
        )[MatchesViewModel::class.java]

        binding.swipeContainer.setOnRefreshListener {
            getMatches()
        }

        binding.swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter

        viewModel.matches.observe(this, this)

        if (viewModel.matches.value != null) {
            onChanged(viewModel.matches.value)
        } else {
            getMatches()
        }
    }

    override fun onChanged(t: MatchesResponse?) {
        binding.swipeContainer.isRefreshing = false
        t?.let {
            mAdapter.clear()
            mAdapter.addAll(it.matches)
        }
    }

    private fun getMatches() {
        if (Utils.checkForInternet(this)) {
            viewModel.getMatchesData()
        } else {
            Utils.showError(this, getString(R.string.lbl_error_network))
            binding.swipeContainer.isRefreshing = false
        }
    }
}