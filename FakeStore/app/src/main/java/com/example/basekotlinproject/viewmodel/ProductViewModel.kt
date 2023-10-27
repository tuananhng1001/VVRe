package com.example.basekotlinproject.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basekotlinproject.base.BaseViewModel
import com.example.basekotlinproject.data.repository.ProductsRepository
import com.example.basekotlinproject.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor (
    override val app: Application,
    private val productsRepository : ProductsRepository,
    ) : BaseViewModel(app) {
    private var _products = MutableLiveData<Product>()
    val products: LiveData<Product>
        get() = _products

    fun getProducts(){
        viewModelScope.launch {
            _products.value = productsRepository.getProducts().data!!
        }
    }
}