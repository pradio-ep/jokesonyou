package com.pradioep.jokesonyou.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.pradioep.jokesonyou.Constant
import com.pradioep.jokesonyou.R
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.ui.detail.DetailActivity

class CategoryAdapter(private val activity: Activity, private val categories: ArrayList<String>,
                      private val listener: CategoryListener) : BaseAdapter() {

    private val listColor = arrayListOf(
            ContextCompat.getColor(activity, R.color.green),
            ContextCompat.getColor(activity, R.color.yellow),
            ContextCompat.getColor(activity, R.color.red),
            ContextCompat.getColor(activity, R.color.black),
            ContextCompat.getColor(activity, R.color.yellow),
            ContextCompat.getColor(activity, R.color.blue),
            ContextCompat.getColor(activity, R.color.red),
            ContextCompat.getColor(activity, R.color.black),
            ContextCompat.getColor(activity, R.color.green),
            ContextCompat.getColor(activity, R.color.blue),
            ContextCompat.getColor(activity, R.color.red),
            ContextCompat.getColor(activity, R.color.black),
            ContextCompat.getColor(activity, R.color.blue),
            ContextCompat.getColor(activity, R.color.red),
            ContextCompat.getColor(activity, R.color.green),
            ContextCompat.getColor(activity, R.color.yellow),
    )

    interface CategoryListener {
        fun onClickCategory(category: String)
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view  = LayoutInflater.from(activity).inflate(R.layout.item_category,null)

        val cardCategory = view.findViewById<CardView>(R.id.card_category)
        val txtCategory = view.findViewById<TextView>(R.id.txt_category)

        cardCategory.setCardBackgroundColor(listColor[position])
        cardCategory.setOnClickListener {
            listener.onClickCategory(categories[position])
        }

        txtCategory.text = categories[position]

        return view
    }
}