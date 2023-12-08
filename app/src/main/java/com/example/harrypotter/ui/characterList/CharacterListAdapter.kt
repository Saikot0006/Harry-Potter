package com.example.harrypotter.ui.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.util.Util
import com.example.harrypotter.databinding.ItemCharacterBinding
import com.example.harrypotter.models.CharactersListResponse

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>(){

    private val dataList : MutableList<CharactersListResponse.CharactersListResponseItem> = mutableListOf()
    var callBack : ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class CharacterListViewHolder(val binding : ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(characterItem : CharactersListResponse.CharactersListResponseItem){
                binding.item = characterItem

                if(characterItem.name.isNullOrEmpty()){
                    binding.characterItemNameTV.visibility = View.GONE
                }else{
                    binding.characterItemNameTV.visibility = View.VISIBLE
                }

                if(characterItem.actor.isNullOrEmpty()){
                    binding.characterItemActorNameTV.visibility = View.GONE
                }else{
                    binding.characterItemActorNameTV.visibility = View.VISIBLE
                }

                if(characterItem.house.isNullOrEmpty()){
                    binding.characterItemHouseNameTV.visibility = View.GONE
                }else{
                    binding.characterItemHouseNameTV.visibility = View.VISIBLE
                }

                binding.root.setOnClickListener {
                    callBack?.invoke(characterItem.id ?: "")
                }
            }
        }

    fun addItem(dataList : List<CharactersListResponse.CharactersListResponseItem>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}