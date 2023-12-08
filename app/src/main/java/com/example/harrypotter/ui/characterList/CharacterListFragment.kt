package com.example.harrypotter.ui.characterList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentCharacterListBinding
import com.example.harrypotter.ui.viewModel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding : FragmentCharacterListBinding
    private val characterViewModel : CharacterViewModel by viewModels()
    private lateinit var characterAdapter : CharacterListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterListBinding.inflate(layoutInflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = characterViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onClick()
        setUpObserver()
    }

    private fun initView() {
        characterViewModel.getCharacters()

        characterAdapter = CharacterListAdapter()
        with(binding.characterRV) {
            layoutManager = LinearLayoutManager(activity)
            adapter = characterAdapter
        }


    }

    private fun setUpObserver() {
        characterViewModel.characterList.observe(viewLifecycleOwner) {
            characterAdapter.addItem(it)
            Log.d("characterViewModel", "setUpObserver: $it")
        }
    }

    private fun onClick() {
        binding.refreshBtn.setOnClickListener {
            characterViewModel.getCharacters()
        }

        characterAdapter.callBack = {characterID ->
            val bundle = Bundle()
            bundle.putString("id",characterID)
            findNavController().navigate(R.id.action_characterListFragment_to_characterDetailsFragment,bundle)
        }
    }



}