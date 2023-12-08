package com.example.harrypotter.ui.characterDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.harrypotter.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding : FragmentCharacterDetailsBinding
    private val characterDetailsViewModel : CharacterDetailsViewModel by viewModels()
    private var characterID : String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = characterDetailsViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClick()
    }

    private fun initView() {
        characterID = arguments?.getString("id").toString()
        characterID?.let {
            characterDetailsViewModel.getCharactersDetailsByID(it)
        }

    }

    private fun onClick() {
        binding.refreshBtn.setOnClickListener {
            characterID?.let {
                characterDetailsViewModel.getCharactersDetailsByID(it)
            }
        }
    }



}