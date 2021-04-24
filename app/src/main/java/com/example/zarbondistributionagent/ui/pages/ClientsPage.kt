package com.example.zarbondistributionagent.ui.pages

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.clientmodel.Client
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.data.models.productsmodel.ProductData
import com.example.zarbondistributionagent.ui.adapters.ClientListAdapter
import com.example.zarbondistributionagent.ui.dialogs.ClientsFilterDialog
import com.example.zarbondistributionagent.ui.viewmodels.ClientPageViewModel
import com.example.zarbondistributionagent.utils.log
import com.example.zarbondistributionagent.utils.showToast
import kotlinx.android.synthetic.main.clients_fragment.*


@Suppress("DEPRECATION")
class ClientsPage : Fragment(R.layout.clients_fragment) {

    lateinit var clientAdapter: ClientListAdapter
    private val viewModel: ClientPageViewModel by viewModels()
    lateinit var recycler : RecyclerView
    lateinit var dt :ClientsData
     var clientsData:  List<Client> = ArrayList()
    private var querySt = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerClients)


        clientsSetUp()

        val closeButton = searchClientView.findViewById(R.id.search_close_btn) as ImageView
        closeButton.setOnClickListener {
            viewModel.closeSearch()
        }

        searchClients.setOnClickListener {
            initClientsChooseDialog()
        }

        refreshClients.setOnRefreshListener {
            viewModel.getClients()
            Handler().postDelayed(Runnable {
                refreshClients.isRefreshing = false
            }, 1000)
        }

            val handler = Handler()
            searchClientView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    if (query != null) {
                        querySt = query.trim()
                        initRecycler(clientsData.filter {
                            it.name.contains(
                                querySt,
                                true
                            )
                        })
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        if (newText != null) {
                            querySt = newText.trim()
                            initRecycler(clientsData.filter {
                                it.name.contains(
                                    querySt,
                                    true
                                )
                            })
                        }
                    }, 500)
                    return true
                }
            })

    }

    private val closeSearchObserver = Observer<Unit> {
        searchClientView.setQuery(null, false)
        searchClientView.clearFocus()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    private val progressObserver = Observer<Boolean> {
        if (it) {
            clientsProgressBar.visibility =View.VISIBLE
        } else {
            clientsProgressBar.visibility =View.GONE
        }
    }
    private val errorClientsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
        clientsProgressBar.visibility =View.GONE
    }
    private val connectionErrorObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }
    private val successClientsObserver = Observer<ClientsData> { list ->
        clientsData = list.clients
        dt = list
        initRecycler(list.clients)
    }



    fun initRecycler(data: List<Client>) {
        clientAdapter = ClientListAdapter()
        clientAdapter.submitList(data)
        clientAdapter.query = querySt
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = clientAdapter
    }

    private fun initClientsChooseDialog() {
        val dialog = ClientsFilterDialog(requireContext())
        dialog.show()
        dialog.setOnClientFilterChosen { filter ->
            if(filter =="all")
           initRecycler(dt.clients)
            else{
                dt.last_month_clients
            }
        }
    }

    fun clientsSetUp() {
        viewModel.closeLiveData.observe(viewLifecycleOwner, closeSearchObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.errorCategoriesLiveData.observe(viewLifecycleOwner, errorClientsObserver)
        viewModel.connectionErrorLiveData.observe(viewLifecycleOwner, connectionErrorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successClientsObserver)
    }
}