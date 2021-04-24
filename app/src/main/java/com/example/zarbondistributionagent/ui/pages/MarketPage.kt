package com.example.zarbondistributionagent.ui.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.zarbondistributionagent.R
import com.example.zarbondistributionagent.data.models.clientmodel.Client
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import com.example.zarbondistributionagent.data.models.getproduct.productFullData
import com.example.zarbondistributionagent.data.models.productsmodel.ProductData
import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.ui.dialogs.ClientChooseDialog
import com.example.zarbondistributionagent.ui.dialogs.GetProductDialog
import com.example.zarbondistributionagent.ui.viewmodels.ClientPageViewModel
import com.example.zarbondistributionagent.ui.viewmodels.SellProductViewModel
import com.example.zarbondistributionagent.utils.showToast
import kotlinx.android.synthetic.main.product_sell_fragment.*

class MarketPage : Fragment(R.layout.product_sell_fragment) {

    var productName = ""
    var productId = -1
    var priceId = -1
    var price = -1
    var clientId = -1
    var discountValue = 0
    var chosenProduct: ProductData? = null
    var sellOrdersId = ArrayList<Int>()


    private val viewModel: ClientPageViewModel by viewModels()
    var clientsData: List<Client> = ArrayList()

    private val sellViewModel: SellProductViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberPicker.maxValue = 0
        numberPicker.minValue = 0

        clientsSetUp()
        getProductSetUp()
        sellProductSetUp()
        TextChanged(inputQuantity)
        initFreeProduct()
        sellViewModel.getDiscount()

        val addDialog = GetProductDialog()

        chooseProduct.setOnClickListener {
            addDialog.show(childFragmentManager, "add")
        }


        addDialog.getProduct {
            addDialog.dismiss()
            initViewProductView(it)
        }


        chooseClient.setOnClickListener {
            if (clientsData.isNotEmpty()) {
                clientChosenChooseDialog(clientsData)
            } else {
                viewModel.getClients()
                requireActivity().showToast("Haridorlar listi bo'sh!")
            }
        }

        sellProduct.setOnClickListener {
            when {
                inputQuantity.text.toString().isEmpty() -> inputQuantity.error =
                    "Miqdorini kiriting!"
                clientName.text.isEmpty() -> requireActivity().showToast("Haridorni tanlang!")
                sellProductName.text.isEmpty() -> requireActivity().showToast("Mahsulot tanlang!")
                else -> {
                    val quantity = inputQuantity.text.toString().toDouble()
                    discountValue = numberPicker.value

                    if (quantity > 0) {
                        val sellData = SellProductData(
                            inputQuantity.text.toString(),
                            clientId,
                            productId,
                            discountValue,
                            priceId,
                        )
                        sellViewModel.sellProduct(sellData)
                    } else requireActivity().showToast("Yaroqli miqdorni kiriting!")
                }
            }
        }

        sellFreeProduct.setOnClickListener {
            when {
                inputQuantity.text.toString().isEmpty() -> {
                    inputQuantity.error = "Miqdorini kiriting!"
                }
                sellOrdersId.isEmpty() -> {
                    requireActivity().showToast("Avval mahsulot soting!")
                }
                clientName.text.isEmpty() -> requireActivity().showToast("Haridorni tanlang!")
                else -> {
                    val quantity = inputQuantity.text.toString().toDouble()
                    if (quantity > 0) {
                        val sellData = FreeProductData(
                            quantity,
                            TokenSaver.getAgentId(),
                            productId,
                            sellOrdersId
                        )
                        sellViewModel.sellFreeProducts(sellData)
                    } else requireActivity().showToast("Miqdorini kiriting!")
                }
            }
        }


        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            discountValue = newVal
            discountText.text = newVal.toString() + " %"
            setTotalPrice()
        }
    }

    private fun initViewProductView(it: ProductData) {
        chosenProduct = it
        productPrice.text = it.price.toString()
        sellProductName.text = it.name
        totalAmount.text = it.quantity
        unitText.text = it.unit
        productId = it.id
        priceId = it.price_id
        price = it.price
        productName = it.name
    }

    private val progressProductObserver = Observer<Boolean> {
        if (it) {
            sellProgressBar.visibility = View.VISIBLE
        } else {
            sellProgressBar.visibility = View.GONE
        }
    }

    private val errorProductObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
        sellProgressBar.visibility = View.GONE
    }


    private val connectionErrorProductObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }


    @SuppressLint("SetTextI18n")
    private val successProductObserver = Observer<productFullData> { response ->
        sellProductName.text = response.name
        unitText.text = response.unit
        productName = response.name
        productPrice.text = response.price.toString()
        priceId = response.price_id

        price = response.price

        if (response.product_type == "limited")
            totalAmount.text = response.quantity.toString() + " ${response.unit}"
        else if (response.product_type == "unlimited") {
            totalAmount.text = "Vip"
        }
        productId = response.id
    }


    private val progressSellObserver = Observer<Boolean> {
        if (it) {
            sellProgressBar.visibility = View.VISIBLE
        } else {
            sellProgressBar.visibility = View.GONE
        }
    }


    private val errorSellObserver = Observer<String> {
        requireActivity().showToast("Ulanishda xatolik!")
        sellProgressBar.visibility = View.GONE
    }

    private val connectionErrorSellObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }
    private val successSellObserver = Observer<SellProductResponse> { response ->
        if (response.client == clientId && response.product == productId) {
            initDialog()
            sellOrdersId.add(response.id)
            Toast.makeText(
                requireContext(),
                "Ushbu haridorga " + sellOrdersId.size + " ta mahsulot sotdingiz!",
                Toast.LENGTH_LONG
            ).show()

        }

    }

    private val successGetDiscount = Observer<PermittedDiscountData> { response ->
        numberPicker.maxValue = response.discount
    }


    private val errorResponseLiveDataDiscount = Observer<String> {
        requireActivity().showToast(it)
    }

    private fun initDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Diqqat!")
            .setMessage("So'rovingiz muvaffqiyatli amalga oshdi. Tasdiqlash uchun yuborildi!")
            .setPositiveButton("Ok") { dialog, _ ->
                numberPicker.minValue = 0
                inputQuantity.setText("")
                dialog.cancel()
            }.show()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun sellProductSetUp() {
        sellViewModel.progressSellLiveData.observe(this, progressSellObserver)
        sellViewModel.errorResponseLiveDataDiscount.observe(this, errorResponseLiveDataDiscount)
        sellViewModel.errorNotResponseLiveDataDiscount.observe(this, errorSellObserver)
        sellViewModel.connectionErrorLiveData.observe(
            this,
            connectionErrorSellObserver
        )
        sellViewModel.successLiveData.observe(this, successSellObserver)
    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun getProductSetUp() {
        sellViewModel.successLiveDataDiscount.observe(this, successGetDiscount)

        sellViewModel.progressProductLiveData.observe(this, progressProductObserver)
        sellViewModel.connectionErrorProductLiveData.observe(
            this, connectionErrorProductObserver
        )
        sellViewModel.successProductLiveData.observe(this, successProductObserver)
        sellViewModel.errorProductLiveData.observe(this, errorProductObserver)
    }


    private val progressObserver = Observer<Boolean> {
        if (it) {
            sellProgressBar.visibility = View.VISIBLE
        } else {
            sellProgressBar.visibility = View.GONE
        }
    }
    private val errorClientsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
        sellProgressBar.visibility = View.GONE
    }
    private val connectionErrorObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }
    private val successClientsObserver = Observer<ClientsData> { list ->
        clientsData = list.clients
    }

    private fun clientChosenChooseDialog(data: List<Client>) {
        val dialog = ClientChooseDialog(requireContext(), data)
        dialog.show()
        dialog.setOnClientChosen { id, name, totalDebt ->
            clientName.text = name
            if (clientId != id) sellOrdersId.clear()
            clientId = id
        }
    }

    private fun clientsSetUp() {
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.errorCategoriesLiveData.observe(viewLifecycleOwner, errorClientsObserver)
        viewModel.connectionErrorLiveData.observe(viewLifecycleOwner, connectionErrorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successClientsObserver)
    }


    private val progressObserverFreeProduct = Observer<Boolean> {
        if (it) {
            sellProgressBar.visibility = View.VISIBLE
        } else {
            sellProgressBar.visibility = View.GONE
        }
    }

    private val errorLoginObserverFreeProduct = Observer<String> {
        requireActivity().showToast("Xato!")
    }
    private val connectionErrorObserverFreeProduct = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }

    private val successFreeProduct = Observer<Any> {

        AlertDialog.Builder(requireContext())
            .setTitle("Diqqat!")
            .setMessage("${clientName.text} ga ${productName} dan ${inputQuantity.text} ${unitText.text} chegirma qilib berildi.")
            .setPositiveButton("Ok") { dialog, _ ->
                sellOrdersId.clear()
                clearAll()
                dialog.cancel()
            }.show()
    }

    fun clearAll() {
        productName = ""
        productId = -1
        priceId = -1
        price = -1
        clientId = -1
        chosenProduct = null
        sellProductName.text = ""
        totalAmount.text = ""
        clientName.text = ""
        productPrice.text = ""
        totalPrice.text = ""
        numberPicker.minValue = 0
    }


    @SuppressLint("FragmentLiveDataObserve")
    fun initFreeProduct() {
        sellViewModel.progressLiveDataFreeProduct.observe(
            this,
            progressObserverFreeProduct
        )
        sellViewModel.errorLoginLiveDataFreeProduct.observe(
            this,
            errorLoginObserverFreeProduct
        )
        sellViewModel.connectionErrorLiveDataFreeProduct.observe(
            this,
            connectionErrorObserverFreeProduct
        )
        sellViewModel.successFreeProduct.observe(
            this,
            successFreeProduct
        )
    }


    fun handlerEvent(id: Int) {
        sellViewModel.getProduct(id.toString())
        sellViewModel.getDiscount()
    }

    fun TextChanged(editText: EditText) {

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int,
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setTotalPrice()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setTotalPrice() {
        if (inputQuantity.text.toString().isNotEmpty() && price != -1) {
            val quantity = inputQuantity.text.toString().toDouble()
            if (quantity > 0.0 && price > 0.0)
                totalPrice.text =
                    "%.1f".format(quantity * (price - price * discountValue / 100))
        } else totalPrice.text = ""
    }
}