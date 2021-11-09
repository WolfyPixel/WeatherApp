package com.company.restapplication.activities

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.company.restapplication.OurApplication
import com.company.restapplication.adapters.WeatherAdapter
import com.company.restapplication.databinding.ActivityMainBinding
import com.company.restapplication.databinding.DialogWeatherBinding
import com.company.restapplication.db.models.Weather
import com.company.restapplication.view_models.WeatherViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), WeatherAdapter.ItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var app: OurApplication

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        initAdapter()
        initViewModel()
        initOthers()
    }

    private fun initViewModel() {
        viewModel.event.observe(this, {
            when(it){
                is WeatherViewModel.Event.OnError -> Toast.makeText(this, it.msg, Toast.LENGTH_LONG).show()
                is WeatherViewModel.Event.OnItemSelected -> showItemDialog(it.item)
            }
        })
        viewModel.items.observe(this, {
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, {
            binding.progress.visibility = if(it)
                View.VISIBLE
            else
                View.GONE
        })
    }

    private fun initAdapter() {
        adapter = WeatherAdapter(arrayListOf(), this)

        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initOthers(){
        binding.editSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH){
                viewModel.searchText = binding.editSearch.text.toString()
                true
            } else
                false
        }

        viewModel.getItems()
    }

    private fun showItemDialog(item: Weather){
        val dialog = Dialog(this)
        val dialogBinding = DialogWeatherBinding.inflate(layoutInflater)

        dialogBinding.textName.text = item.city_name
        dialogBinding.textDescription.text = item.temp.toString()

        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }

    override fun onClick(item: Weather, position: Int) {
        viewModel.selectItem(item)
    }
}