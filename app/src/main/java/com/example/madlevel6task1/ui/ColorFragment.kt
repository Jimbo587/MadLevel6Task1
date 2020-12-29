package com.example.madlevel6task1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task1.model.ColorItem
import com.example.madlevel6task1.viewmodel.ColorViewModel
import com.example.madlevel6task1.databinding.FragmentColorBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ColorFragment : Fragment() {
    private val colors = arrayListOf<ColorItem>()
    private lateinit var colorAdapter: ColorAdapter
    private val viewModel: ColorViewModel by viewModels()
    private var _binding: FragmentColorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorAdapter = ColorAdapter(colors, ::onColorClick)
        binding.rvColor.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvColor.adapter = colorAdapter

        observeColors()
    }

    private fun observeColors() {
        viewModel.colorItems.observe(viewLifecycleOwner, {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }

    private fun onColorClick(colorItem: ColorItem) {
        Snackbar.make(binding.rvColor, "This color is: ${colorItem.name}", Snackbar.LENGTH_LONG)
            .show()
    }
}
