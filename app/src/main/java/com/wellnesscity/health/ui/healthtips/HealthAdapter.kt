package com.wellnesscity.health.ui.healthtips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.databinding.ItemLayoutBinding
import com.wellnesscity.health.databinding.ShimmerLayoutBinding

/**
 * Created by Loveth Nwokike on 25/10/2020
 * */
class HealthAdapter : ListAdapter<HealthTipX, HealthAdapter.HealthViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        return HealthViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class HealthViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(healthTipX: HealthTipX) {
            binding.descTv.text = healthTipX.detail
            binding.titleTv.text = healthTipX.topic
            binding.image.load(healthTipX.icon_url)
        }
    }

    object DIFF_CALLBACK : DiffUtil.ItemCallback<HealthTipX>() {
        override fun areItemsTheSame(oldItem: HealthTipX, newItem: HealthTipX) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HealthTipX, newItem: HealthTipX) =
            oldItem == newItem
    }


}