package com.pradioep.jokesonyou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pradioep.jokesonyou.R
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.util.UtilityHelper

class SearchAdapter(private val context : Context, private val list: ArrayList<Result>,
                    private val listener: SearchListener) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    interface SearchListener {
        fun onClickSearchResult(result: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search, parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.txtSearchJoke.text = list[position].value
        holder.txtSearchDate.text = UtilityHelper.getSdfDayDate(list[position].created_at)
        holder.itemView.setOnClickListener {
            listener.onClickSearchResult(list[position])
        }
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtSearchJoke: TextView = itemView.findViewById(R.id.txt_search_joke)
        val txtSearchDate: TextView = itemView.findViewById(R.id.txt_search_date)
    }
}