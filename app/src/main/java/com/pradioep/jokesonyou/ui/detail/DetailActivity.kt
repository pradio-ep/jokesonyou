package com.pradioep.jokesonyou.ui.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.pradioep.jokesonyou.Constant
import com.pradioep.jokesonyou.R
import com.pradioep.jokesonyou.databinding.ActivityDetailBinding
import com.pradioep.jokesonyou.ui.base.BaseActivity
import com.pradioep.jokesonyou.util.UtilityHelper
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(android.R.id.content).systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        with(viewModel) {
            isLoading.observe(this@DetailActivity, { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            showMessage.observe(this@DetailActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            serverError.observe(this@DetailActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            networkError.observe(this@DetailActivity, {
                if (it is String) {
                    UtilityHelper.showErrorMessage(view_parent, it)
                }
            })
            randomJoke.observe(this@DetailActivity, {
                setJoke(it.value, it.created_at)
            })
            clickClose.observe(this@DetailActivity, {
                finish()
            })
        }

        setView()
    }

    private fun setView() {
        val searchResult = intent.getBooleanExtra(Constant.SEARCH_RESULT, false)
        if (searchResult) {
            val searchJoke = intent.getStringExtra(Constant.SEARCH_RESULT_JOKE)
            val searchDate = intent.getStringExtra(Constant.SEARCH_RESULT_DATE)
            setJoke(searchJoke!!, searchDate!!)
        } else {
            val category = intent.getStringExtra(Constant.SELECTED_CATEGORY)
            category?.let { viewModel.getRandomJokeByCategory(it) }
        }
    }

    private fun setJoke(joke: String, date: String) {
        viewModel.joke.value = joke
        viewModel.date.value = UtilityHelper.getSdfDayDate(date)
    }
}