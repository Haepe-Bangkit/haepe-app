package com.example.cemaraapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cemaraapps.model.IntroItem


class IntroAdapter(private val IntroItems:List<IntroItem>):
    RecyclerView.Adapter<IntroAdapter.IntroItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroItemViewHolder {
        return IntroItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.intro_item, parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroItemViewHolder, position: Int) {
        holder.bind(IntroItems[position])
    }

    override fun getItemCount(): Int {
        return IntroItems.size
    }

    inner class IntroItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageIntro)
        private val textTitle = view.findViewById<TextView>(R.id.title_intro)
        private val textDescription = view.findViewById<TextView>(R.id.text_intro)
        fun bind(IntroItem: IntroItem) {
            imageOnboarding.setImageResource(IntroItem.imageIntro)
            textTitle.text = IntroItem.titleIntro
            textDescription.text = IntroItem.descIntro
        }
    }
}