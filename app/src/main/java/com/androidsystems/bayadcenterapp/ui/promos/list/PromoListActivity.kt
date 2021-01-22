package com.androidsystems.bayadcenterapp.ui.promos.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidsystems.bayadcenterapp.R
import com.androidsystems.bayadcenterapp.R.layout
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS
import com.androidsystems.bayadcenterapp.ui.promos.details.PromoDetailsActivity
import kotlinx.android.synthetic.main.activity_promo_list.button
import kotlinx.android.synthetic.main.activity_promo_list.textView
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListActivity : BaseActivity(), OnClickListener {

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
        initViews()
        loadPromos()
    }

    private fun initViews() {
        button.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                val intent = Intent(this, PromoDetailsActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }
}