package com.airtel.addresssuggestions.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.airtel.addresssuggestions.R
import com.airtel.addresssuggestions.ViewModelProviderFactory
import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import com.airtel.addresssuggestions.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import io.reactivex.Observable
import javax.inject.Inject
import io.reactivex.subjects.PublishSubject
import android.widget.ArrayAdapter
import com.airtel.addresssuggestions.data.models.Address


class MainActivity : AppCompatActivity(),MainNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    lateinit var  adapter: ArrayAdapter<Address>

    var mViewDataBinding: ActivityMainBinding? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this,  factory).get(MainViewModel::class.java)
        mainViewModel.navigator = this

        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewDataBinding?.viewModel= mainViewModel

        mainViewModel.setSearchListener(fromView(mViewDataBinding!!.searchViewAddress))

    }


    fun fromView(searchView: AutoCompleteTextView): Observable<String> {

        val subject = PublishSubject.create<String>()

        searchView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              //
             }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject.onNext(s.toString())
            }
        })
        return subject
    }

    override fun updateSuggestionList(response: SuggestedAddressResponse) {
        adapter = ArrayAdapter(this, R.layout.layout_dropdown_child, response.data.addressList)
        mViewDataBinding?.searchViewAddress?.setAdapter(adapter)
        mViewDataBinding?.searchViewAddress?.showDropDown()

    }

    override fun handleError(throwable: Throwable) {
        //
    }
}
