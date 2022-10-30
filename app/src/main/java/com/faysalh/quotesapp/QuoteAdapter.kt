package com.faysalh.quotesapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.faysalh.quotesapp.databinding.ItemQuetesBinding

class QuoteAdapter(val context: Context,val list:List<QuotesModel>,val listener:CopyListener ):RecyclerView.Adapter<QuoteAdapter.QuoterViewModel>() {
    class QuoterViewModel(val binding: ItemQuetesBinding) :ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoterViewModel {
        val binding=ItemQuetesBinding.inflate(LayoutInflater.from(context),parent,false)
        return QuoterViewModel(binding)
    }

    override fun onBindViewHolder(holder: QuoterViewModel, position: Int) {
        holder.binding.textViewQuote.text=list[position].text
        holder.binding.textViewAuthor.text=list[position].author

        holder.binding.buttonCopy.setOnClickListener {
            listener.onCopyClicked(list.get(holder.adapterPosition).text)
            holder.binding.buttonCopy.setBackgroundColor(Color.parseColor("#97A6A6"))

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}