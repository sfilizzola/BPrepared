package com.avenuecode.bprepared.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.avenuecode.bprepared.R
import com.avenuecode.bprepared.adapters.MainListAdapter
import com.avenuecode.bprepared.databinding.FragmentMainListBinding
import com.avenuecode.bprepared.view.viewStatus.MainListViewStatus
import com.avenuecode.bprepared.viewmodels.MainListViewModel
import timber.log.Timber
import javax.inject.Inject

class MainListFragment : BaseFragment(), AdapterView.OnItemSelectedListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainListViewModel::class.java) }

    private lateinit var binding: FragmentMainListBinding

    private lateinit var listAdapter:MainListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_list, container, false)
        binding.viewModel = viewModel

        listAdapter = MainListAdapter(viewModel.getData())

        with(binding.mainList){
            this.setHasFixedSize(true)
            this.adapter = listAdapter
            this.layoutManager = LinearLayoutManager(context)
        }

        setUpSpinner()

        setUpListeners()

        return binding.root

    }



    private fun setUpSpinner() {

        val adapter = ArrayAdapter.createFromResource(activity, R.array.disasters_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mainDisasterType.adapter = adapter
        binding.mainDisasterType.onItemSelectedListener = this
    }

    private fun setUpListeners() {

        viewModel.getData().observe(this, Observer {
            it?.let {
                when (it){
                    is MainListViewStatus.fabClick ->
                    {
                        Timber.d("fabClicked")
                        //TODO - open window for adding item
                    }
                    is MainListViewStatus.Error -> {
                        Timber.e(it.message())
                        displaySnackbarMessage(binding.root, it.message()!!, null)
                    }
                    is MainListViewStatus.ListSuccess ->{
                        Timber.d("ListFilled")
                        listAdapter.update(it.list())
                    }
                    is MainListViewStatus.CheckedItem -> {
                        listAdapter.checkItem(it.item()!!)
                    }
                }
            }
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        viewModel.selectDisaster(0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.selectDisaster(position)
    }
}