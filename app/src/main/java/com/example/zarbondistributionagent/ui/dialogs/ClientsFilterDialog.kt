package com.example.zarbondistributionagent.ui.dialogs

import android.content.Context
import com.example.zarbondistributionagent.R
import kotlinx.android.synthetic.main.clients_dialog.view.*


class ClientsFilterDialog(context: Context) :
    BaseDialog(context, R.layout.clients_dialog) {
    private var listener: ((String) -> Unit)? = null
    init {
        view.apply {

            allClients.setOnClickListener {
                listener?.invoke("all")
                close()
            }

            lastMonth.setOnClickListener {
                listener?.invoke("lastMonth")
                close()
            }

        }
    }

    fun setOnClientFilterChosen(f: ((String) -> Unit)?) {
        listener = f
    }
}

