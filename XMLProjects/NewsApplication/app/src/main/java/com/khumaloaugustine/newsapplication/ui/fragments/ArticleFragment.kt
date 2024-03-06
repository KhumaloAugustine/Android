package com.khumaloaugustine.newsapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.khumaloaugustine.newsapplication.R
import com.khumaloaugustine.newsapplication.databinding.FragmentArticleBinding
import com.khumaloaugustine.newsapplication.ui.NewsActivity
import com.khumaloaugustine.newsapplication.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private lateinit var newsViewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        val activity = activity as? NewsActivity
        activity?.let {
            newsViewModel = it.newsViewModel
        }

        val article = args.article
        article?.let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(it.url)
            }
            binding.fab.setOnClickListener { _ ->
                if (::newsViewModel.isInitialized) {
                    newsViewModel.addToFavourites(it)
                    Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(view, "Failed to add to favourites", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}