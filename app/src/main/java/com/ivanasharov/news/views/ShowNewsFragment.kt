package com.ivanasharov.news.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.ivanasharov.news.R
import com.ivanasharov.news.databinding.FragmentNewsShowBinding

class ShowNewsFragment : Fragment() {

    private val mArguments: ShowNewsFragmentArgs by navArgs()
    private lateinit var mBinding: FragmentNewsShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_show, container, false)
        mBinding.viewModel = mArguments.newsViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

}