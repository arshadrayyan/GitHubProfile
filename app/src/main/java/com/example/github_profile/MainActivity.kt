package com.example.github_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github_profile.data.GHRepoRepository
import com.example.github_profile.data.local.AppDatabase
import com.example.github_profile.data.local.GHRepoLocalDataSource
import com.example.github_profile.data.remote.GHRepoRemoteDataSource
import com.example.github_profile.data.remote.NetworkService
import com.example.github_profile.domain.FetchGHReposUseCase
import com.example.github_profile.presentation.adapter.GHRepoAdapter
import com.example.github_profile.presentation.viewmodel.GHRepoViewModel

// Activity to bind everything together.

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GHRepoViewModel
    private lateinit var adapter: GHRepoAdapter
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize components
        val usernameInput = findViewById<EditText>(R.id.etUsername)
        val searchBtn = findViewById<Button>(R.id.btnSearch)
        val searchQuery = findViewById<EditText>(R.id.etSearchQuery)
        val recyclerView = findViewById<RecyclerView>(R.id.rvRepos)
        webView = findViewById(R.id.webView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GHRepoAdapter(listOf()) { repo ->
            webView.loadUrl(repo.repoURL)
            webView.webViewClient = WebViewClient()
        }
        recyclerView.adapter = adapter

        // Setup ViewModel with factory manually
        val dao = AppDatabase.getDatabase(applicationContext).ghRepoDao()
        val repository = GHRepoRepository(
            GHRepoLocalDataSource(dao),
            GHRepoRemoteDataSource(NetworkService())
        )
        val useCase = FetchGHReposUseCase(repository)
        viewModel = GHRepoViewModel(useCase)

        // Observe data
        viewModel.repos.observe(this) { repos -> adapter.updateList(repos) }
        viewModel.error.observe(this) { error -> error?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() } }

        // Listeners
        searchBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            if (username.isNotEmpty()) {
                viewModel.fetchRepos(username)
            }
        }

        searchQuery.setOnEditorActionListener { _, _, _ ->
            val query = searchQuery.text.toString()
            viewModel.searchRepos(query)
            true
        }
    }
}