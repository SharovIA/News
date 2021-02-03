package com.ivanasharov.news.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ivanasharov.news.R
import com.ivanasharov.news.databinding.FragmentListNewsBinding
import com.ivanasharov.news.viewmodel.ListNewsViewModel
import com.ivanasharov.news.views.adapters.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNewsFragment : Fragment() {

    private val mListNewsViewModel: ListNewsViewModel by viewModels()
    private lateinit var mBinding: FragmentListNewsBinding
    private lateinit var mAdapter: NewsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_news, container, false)
        mBinding.viewModel = mListNewsViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        return mBinding.root
    }

    private fun initRecyclerView() {
        mBinding.newsRecyclerView.apply {
            mAdapter = NewsRecyclerViewAdapter()
            adapter = mAdapter
        }

        mBinding.searchEditText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mAdapter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListNewsViewModel.newsList.observe(viewLifecycleOwner, Observer{
            mAdapter.submitList(it)
        })
    }
}