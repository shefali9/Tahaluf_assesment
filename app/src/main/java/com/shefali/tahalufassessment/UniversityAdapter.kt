package com.shefali.tahalufassessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UniversityAdapter(private val onClick: (University) -> Unit) :
    ListAdapter<University, UniversityAdapter.UniversityViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_university, parent, false)
        return UniversityViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = getItem(position)

        holder.bind(university)
    }

    class UniversityViewHolder(itemView: View, val onClick: (University) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val countryTextView: TextView = itemView.findViewById(R.id.textViewCountry)
        private var currentUniversity: University? = null

        init {
            itemView.setOnClickListener {
                currentUniversity?.let {
                    onClick(it)
                }
            }
        }

        fun bind(university: University) {
            currentUniversity = university
            nameTextView.text = university.name
            countryTextView.text = university.country
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<University>() {
            override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
                return oldItem == newItem
            }
        }
    }
}
