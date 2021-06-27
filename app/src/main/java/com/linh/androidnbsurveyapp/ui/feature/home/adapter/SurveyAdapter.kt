package com.linh.androidnbsurveyapp.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.ui.model.SurveyModel

class SurveyAdapter(private val itemClickListener: (Int) -> Unit) :
    ListAdapter<SurveyModel, SurveyAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_home_survey, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, itemClickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val imageCover = itemView.findViewById<ImageView>(R.id.image_cover)
        private var textTitle = itemView.findViewById<TextView>(R.id.text_title)
        private var textDescription = itemView.findViewById<TextView>(R.id.text_description)

        init {
            itemView.setOnClickListener {
                itemClickListener.invoke(adapterPosition)
            }
        }

        fun bind(surveyModel: SurveyModel) {
            textTitle.text = surveyModel.title
            Glide
                .with(itemView.context)
                .load(surveyModel.highQualityCoverImage())
                .centerCrop()
                .into(imageCover)
            textDescription.text = surveyModel.description
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<SurveyModel>() {

        override fun areItemsTheSame(oldItem: SurveyModel, newItem: SurveyModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SurveyModel, newItem: SurveyModel): Boolean {
            return oldItem == newItem
        }
    }
}