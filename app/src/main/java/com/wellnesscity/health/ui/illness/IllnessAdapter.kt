package com.wellnesscity.health.ui.illness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import com.wellnesscity.health.databinding.ShimmerLayoutBinding

class IllnessAdapter : ListAdapter<ConditionsWithSymptom, IllnessAdapter.IllnessViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IllnessViewHolder {
        return IllnessViewHolder(ShimmerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: IllnessViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class IllnessViewHolder(private val binding: ShimmerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conditions: ConditionsWithSymptom) {
            binding.descTv.text = conditions.desc
            binding.titleTv.text = conditions.condition
            binding.image.load(conditions.img_url)
        }
    }

    object DIFF_CALLBACK : DiffUtil.ItemCallback<ConditionsWithSymptom>() {
        override fun areItemsTheSame(oldItem: ConditionsWithSymptom, newItem: ConditionsWithSymptom) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ConditionsWithSymptom, newItem: ConditionsWithSymptom) =
            oldItem == newItem
    }



}