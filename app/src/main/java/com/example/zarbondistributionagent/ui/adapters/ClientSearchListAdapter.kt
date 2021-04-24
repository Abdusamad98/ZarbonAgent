package com.example.zarbondistributionagent.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.clientmodel.Client
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.utils.spannableText
import kotlinx.android.synthetic.main.item_search_client.view.*

class ClientSearchListAdapter :
    ListAdapter<Client, ClientSearchListAdapter.ViewHolder>(DiffItem) {

    var query = ""

    object DiffItem : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.responsible_agent == newItem.responsible_agent &&
                    oldItem.phone_number1 == newItem.phone_number1 &&
                    oldItem.debt == newItem.debt
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search_client, parent, false)
    )

    private var listenerClientData: ((Int, String, Double) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {
                clientSearchableName.setOnClickListener {
                    listenerClientData?.invoke(
                        getItem(adapterPosition).id,
                        getItem(adapterPosition).name,
                        getItem(adapterPosition).debt
                    )
                }
            }
        }
//        if (query != "") productName.text = d.name spannableText query
//        else productName.text = d.name

        fun bind(d: Client) {
            itemView.apply {
                if (query != "") clientSearchableName.text = d.name spannableText query
                else clientSearchableName.text = d.name
            }
        }
    }


    fun setOnClientChosenListener(f: (Int, String, Double) -> Unit) {
        listenerClientData = f
    }

}
