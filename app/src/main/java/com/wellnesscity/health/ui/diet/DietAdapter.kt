package com.wellnesscity.health.ui.diet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wellnesscity.health.data.model.Diet
import com.wellnesscity.health.databinding.DietItemLayoutBinding


/**
 * Created by Loveth Nwokike on 28/10/2020
 */
class DietAdapter : ListAdapter<Diet, DietAdapter.DietViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        return DietViewHolder(DietItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class DietViewHolder(private val binding: DietItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(diet: Diet) {
            binding.titleTv.text = diet.title
            binding.image.load(diet.image)
        }
    }

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Diet>() {
        override fun areItemsTheSame(oldItem: Diet, newItem: Diet) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Diet, newItem: Diet) =
            oldItem == newItem
    }


}