package com.pradioep.jokesonyou.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradioep.jokesonyou.Constant
import com.pradioep.jokesonyou.R
import com.pradioep.jokesonyou.adapter.CategoryAdapter
import com.pradioep.jokesonyou.adapter.SearchAdapter
import com.pradioep.jokesonyou.databinding.ActivityMainBinding
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.ui.base.BaseActivity
import com.pradioep.jokesonyou.ui.detail.DetailActivity
import com.pradioep.jokesonyou.util.UtilityHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity(), SearchAdapter.SearchListener, CategoryAdapter.CategoryListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(android.R.id.content).systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        with(viewModel) {
            isLoading.observe(this@MainActivity, { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            showMessage.observe(this@MainActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            serverError.observe(this@MainActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            networkError.observe(this@MainActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            clickBack.observe(this@MainActivity, {
                et_search.clearFocus()
                ic_back.setImageResource(R.drawable.ic_search)
                et_search.setText("")
            })
            clickClose.observe(this@MainActivity) {
                et_search.setText("")
                ic_close.visibility = View.GONE
                rv_search.visibility = View.GONE
            }
            listSearch.observe(this@MainActivity, {
                setListSearch(it)
            })
            listCategory.observe(this@MainActivity, {
                setCategory(it)
            })
        }

        setView()
    }

    private fun setView() {
        setupSearch()
        viewModel.getCategories()
    }

    var delay: Long = 1000 // 1 seconds after user stops typing
    var query: CharSequence = ""
    var lastTextEdit: Long = 0
    var handler: Handler = Handler()

    private val inputFinishChecker = Runnable {
        if (System.currentTimeMillis() > lastTextEdit + delay - 500) {
            if (query.isEmpty()) {
                ic_close.visibility = View.GONE
            } else if (query.isNotEmpty()) {
                ic_close.visibility = View.VISIBLE
            }
            if (query.length > 2) {
                viewModel.searchJokes(query.toString())
                rv_search.visibility = View.VISIBLE
            } else {
                rv_search.visibility = View.GONE
            }
        }
    }

    private fun setupSearch() {
        et_search.setOnFocusChangeListener { _ , _ ->
            ic_back.setImageResource(R.drawable.ic_back)
        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                query = s.toString().toLowerCase(Locale.getDefault())
                handler.removeCallbacks(inputFinishChecker)
            }
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isNotEmpty()) {
                        lastTextEdit = System.currentTimeMillis()
                        handler.postDelayed(inputFinishChecker, delay)
                    }
                }
            }
        })
    }

    private fun setListSearch(list: ArrayList<Result>){
        rv_search.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SearchAdapter(this@MainActivity, list, this@MainActivity).also {
                it.notifyDataSetChanged()
            }
        }
    }

    override fun onClickSearchResult(result: Result) {
        UtilityHelper.hideSoftKeyboard(this)
        rv_search.visibility = View.GONE

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constant.SEARCH_RESULT, true)
        intent.putExtra(Constant.SEARCH_RESULT_JOKE, result.value)
        intent.putExtra(Constant.SEARCH_RESULT_DATE, result.created_at)
        startActivity(intent)
    }

    private fun setCategory(listCategory: ArrayList<String>) {
        grid_category.apply {
            adapter = CategoryAdapter(this@MainActivity, listCategory, this@MainActivity)
        }
    }

    override fun onClickCategory(category: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constant.SELECTED_CATEGORY, category)
        startActivity(intent)
    }
}