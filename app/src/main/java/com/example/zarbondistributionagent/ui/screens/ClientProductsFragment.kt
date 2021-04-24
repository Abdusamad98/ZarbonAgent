package com.example.zarbondistributionagent.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.clientmodel.Client
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.data.models.clientmodel.clientproducts.ClientProducts
import com.example.zarbondistributionagent.ui.adapters.ClientProductListAdapter
import com.example.zarbondistributionagent.ui.dialogs.ClientChooseDialog
import com.example.zarbondistributionagent.ui.viewmodels.ClientPageViewModel
import com.example.zarbondistributionagent.ui.viewmodels.ClientProductsViewModel
import com.example.zarbondistributionagent.utils.showToast
import kotlinx.android.synthetic.main.client_products_fragment.*

class ClientProductsFragment : Fragment(R.layout.client_products_fragment) {

    var clientId = -1
    private val viewModel: ClientPageViewModel by viewModels()
    var clientsData: List<Client> = ArrayList()

    private val productsAdapter by lazy { ClientProductListAdapter() }
    private val pageViewModel: ClientProductsViewModel by viewModels()
    var productData: List<ClientProducts> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clientsSetUp()
        productsSetUp()

        backToHomeClientProducts.setOnClickListener {
            findNavController().navigateUp()
        }



        searchByClient.setOnClickListener {
            if (clientsData.isNotEmpty()) {
                clientChosenChooseDialog(clientsData)
            } else requireActivity().showToast("Haridorlar listi topilmadi!")
        }

    }


    private val progressObserver = Observer<Boolean> {
        if (it) {
            clientProductsProgressBar.visibility = View.VISIBLE
        } else {
            clientProductsProgressBar.visibility = View.GONE
        }
    }
    private val errorClientsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
        clientProductsProgressBar.visibility = View.GONE

    }
    private val connectionErrorObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }
    private val successClientsObserver = Observer<ClientsData> { list ->
        clientsData = list.clients
        if (list.clients.isNotEmpty()) {
            clientId = list.clients[0].id
            clientNameTitle.text = list.clients[0].name
            if (list.clients[0].debt == 0.0) clientDebtTitle.text = "0"
            else clientDebtTitle.text = list.clients[0].debt.toString()
            pageViewModel.getClientProducts(clientId)

        }
    }

    private fun clientChosenChooseDialog(data: List<Client>) {
        val dialog = ClientChooseDialog(requireContext(), data)
        dialog.show()
        dialog.setOnClientChosen { id, name, totalDebt ->
            clientNameTitle.text = name
            if (totalDebt == 0.0) clientDebtTitle.text = "0"
            else clientDebtTitle.text = totalDebt.toString()
            clientId = id
            pageViewModel.getClientProducts(clientId)
        }
    }

    private fun clientsSetUp() {
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.errorCategoriesLiveData.observe(viewLifecycleOwner, errorClientsObserver)
        viewModel.connectionErrorLiveData.observe(viewLifecycleOwner, connectionErrorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successClientsObserver)
    }


    fun productsSetUp() {
        pageViewModel.progressProductsLiveData.observe(viewLifecycleOwner, progressObserver)
        pageViewModel.errorProductsLiveData.observe(viewLifecycleOwner, errorProductsObserver)
        pageViewModel.successProductsLiveData.observe(viewLifecycleOwner, successProductsObserver)
    }


    private val errorProductsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
    }

    private val successProductsObserver = Observer<List<ClientProducts>> { products ->
        productData = products
        initProductsList(products)
    }

    fun initProductsList(data: List<ClientProducts>) {
        productsAdapter.submitList(data)
        recyclerClientProducts.layoutManager = LinearLayoutManager(requireContext())
        recyclerClientProducts.adapter = productsAdapter
    }
}