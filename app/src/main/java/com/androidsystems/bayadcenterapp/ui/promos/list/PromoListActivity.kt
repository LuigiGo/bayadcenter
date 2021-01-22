package com.androidsystems.bayadcenterapp.ui.promos.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidsystems.bayadcenterapp.R.layout
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_promo_list.textView
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListActivity : BaseActivity() {

    @Inject
    lateinit var mFactory: PromoListViewModelFactory

    lateinit var mViewModel: PromoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_promo_list)

        mViewModel = ViewModelProvider(this, mFactory).get(PromoListViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        loadPromos()
    }

    private fun loadPromos() = launch {

        val promos = mViewModel.loadPromoList.await()
        promos.observe(this@PromoListActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    textView.text = it.data.toString()
                }
                else -> {
                }
            }
        })
    }
}