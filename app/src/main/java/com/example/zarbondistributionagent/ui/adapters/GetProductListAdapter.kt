package com.example.zarbondistributionagent.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.productsmodel.ProductData
import com.example.zarbondistributionagent.utils.spannableText
import kotlinx.android.synthetic.main.get_item_products.view.*

class GetProductListAdapter : ListAdapter<ProductData, GetProductListAdapter.ViewHolder>(DiffItem) {

    var query = ""

    private var clickListener : ((ProductData) -> Unit)? = null
    object DiffItem : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.product_type == newItem.product_type &&
                    oldItem.unit == newItem.unit&&
                    oldItem.quantity == newItem.quantity&&
                    oldItem.last_update == newItem.last_update&&
                    oldItem.image == newItem.image&&
                    oldItem.provider == newItem.provider
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.get_item_products, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {
                productSell.setOnClickListener {
                    clickListener?.invoke(getItem(adapterPosition))
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(d: ProductData) {

            itemView.apply {
                if (query != "") productName.text = d.name spannableText query
                else productName.text = d.name

                productPrice.text = d.price.toString()

                if (d.product_type.equals("limited")) {
                    productQuantity.text = d.quantity +" "+ d.unit
                    productStatus.text = "Vip emas"
                    quantityLinear.visibility = View.VISIBLE
                    productStatus.setTextColor(Color.RED)
                } else if (d.product_type.equals("unlimited")) {
                    quantityLinear.visibility = View.GONE
                    productStatus.text = "Vip"
                    productStatus.setTextColor(Color.BLACK)
                }
            }
        }
    }

    fun clickedProduct(f : (ProductData )->Unit) {
        clickListener = f
    }
}