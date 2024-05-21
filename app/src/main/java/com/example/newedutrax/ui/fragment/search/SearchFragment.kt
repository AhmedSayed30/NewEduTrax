package com.example.newedutrax.ui.fragment.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.newedutrax.databinding.FragmentSearchBinding
import com.example.newedutrax.ui.activity.course_details.CourseDetailsActivity


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            checkState()
            recyclerView.adapter = adapter
            layoutSearch.editText?.doOnTextChanged { text, start, before, count ->
                viewModel.search(text.toString())
            }
        }
    }

    private fun checkState() {
        viewModel.vm.observe(
            viewLifecycleOwner
        ){
            adapter.setData(it)
        }
        //adapter.setData(viewModel.vm.value.)
//        lifecycleScope.launch {
//            viewModel.data.collectLatest {
//                it?.let {
//                    adapter.setData(it)
//                }
//            }
//        }
    }
}


