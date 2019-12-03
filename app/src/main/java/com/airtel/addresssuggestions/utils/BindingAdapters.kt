package com.gojek.trendingrepo.utils

import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@BindingAdapter("adapter")
fun setAdapter(view: androidx.recyclerview.widget.RecyclerView, adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>?) {
     view.layoutManager= LinearLayoutManager(view.context)
     view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        view.text = text
    }
}


@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: Int) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        view.text = text.toString()
    }
}

@BindingAdapter("app:bindListToSpinner")
fun setListToSimpleSpinner(view: Spinner, listOfSuggestion: MutableLiveData<ArrayList<String>>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && listOfSuggestion != null) {
        listOfSuggestion.observe(parentActivity, Observer {
            val adapter = ArrayAdapter<String>(parentActivity,android.R.layout.simple_spinner_dropdown_item,it)
            view.setAdapter(adapter)
        })
    }
}

@BindingAdapter("app:selectedIndexToSpinner")
fun setSelectedValueToSimpleSpinner(view: Spinner, selectedIndex: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        selectedIndex?.observe(parentActivity, Observer {
            view.setSelection(it)
        })
    }
}

@BindingAdapter("selectedIndexToSpinnerAttrChanged")
fun changeSelectedIndexSimpleSpinner(view: Spinner, selectedIndexToSpinnerAttrChanged: InverseBindingListener) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedIndexToSpinnerAttrChanged.onChange()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view2: View?, position: Int, id: Long) {
                selectedIndexToSpinnerAttrChanged.onChange()
            }
        }
    }
}

@InverseBindingAdapter(attribute = "selectedIndexToSpinner", event = "selectedIndexToSpinnerAttrChanged")
fun changeSelectedValueToSimpleSpinner(view: Spinner): Int {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        return view.selectedItemPosition
    }
    return 0
}




