package com.example.zarbondistributionagent.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.clientmodel.Client
import com.example.zarbondistributionagent.utils.spannableText
import kotlinx.android.synthetic.main.item_client.view.*

class ClientListAdapter : ListAdapter<Client, ClientListAdapter.ViewHolder>(DiffItem) {

    var query = ""

    object DiffItem :
        DiffUtil.ItemCallback<com.example.zarbondistributionagent.data.models.clientmodel.Client>() {
        override fun areItemsTheSame(
            oldItem: com.example.zarbondistributionagent.data.models.clientmodel.Client,
            newItem: com.example.zarbondistributionagent.data.models.clientmodel.Client
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: com.example.zarbondistributionagent.data.models.clientmodel.Client,
            newItem: com.example.zarbondistributionagent.data.models.clientmodel.Client
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.responsible_agent == newItem.responsible_agent &&
                    oldItem.phone_number1 == newItem.phone_number1 &&
                    oldItem.debt == newItem.debt &&
                    oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {

            }
        }

        fun bind(d: com.example.zarbondistributionagent.data.models.clientmodel.Client) {

            itemView.apply {
                if (query != "") client_name.text = d.name spannableText query
                else client_name.text = d.name

                if (d.image != "") {
                    Glide.with(clientImage.context).load("https://zarbon.herokuapp.com" + d.image)
                        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                        .into(clientImage)
                }


                responsible_agent.text = d.responsible_agent
                phone.text = d.phone_number1
                debt.text = d.debt.toString()

                if (d.debt > 0) {
                    debt.setTextColor(Color.RED)
                } else debt.setTextColor(Color.GREEN)

            }
        }
    }


}