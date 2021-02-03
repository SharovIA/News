package com.ivanasharov.news.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivanasharov.news.databinding.NewsItemBinding
import com.ivanasharov.news.models.NewsViewModel
import com.ivanasharov.news.views.ListNewsFragmentDirections

class NewsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItemsForDisplay: List<NewsViewModel> = ArrayList()
    private var mAllItems: List<NewsViewModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = mItemsForDisplay.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NewsViewHolder -> {
                holder.bind(mItemsForDisplay[position])
            }
        }
    }

    fun submitList(newsList: List<NewsViewModel>){
        mItemsForDisplay = newsList
        mAllItems = newsList
    }

    fun filter(pattern: String){
        if(pattern.isNotEmpty()){
            val newList: MutableList<NewsViewModel> = ArrayList()
            for(i in mAllItems){
                if(checkData(i, pattern))
                    newList.add(i)
            }
            mItemsForDisplay = newList
            notifyDataSetChanged()
        }
        else mItemsForDisplay = mAllItems
    }

    private fun checkData(data: NewsViewModel, pattern: String): Boolean {
        return data.title.contains(pattern) || checkContent(data.description, pattern)
               || checkContent(data.content, pattern)

    }

    private fun checkContent(content: String?, pattern: String): Boolean {
        if(content != null && content.contains(pattern))
            return true
        else return content == null
    }


    class NewsViewHolder constructor(private val mBinding: NewsItemBinding) : RecyclerView.ViewHolder(mBinding.root){

        fun bind(newsViewModel: NewsViewModel) {
            mBinding.viewModel = newsViewModel
            mBinding.itemRecyclerView.setOnClickListener{
                findNavController(mBinding.root).navigate(ListNewsFragmentDirections.actionListNewsFragmentToShowNewsFragment(newsViewModel))

            }
        }
    }

}
