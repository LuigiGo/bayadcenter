package com.androidsystems.bayadcenterapp.ui.promos.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.androidsystems.bayadcenterapp.R.layout
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.INTENT_EXTRAS_PROMO_ITEM
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponseItem
import com.androidsystems.bayadcenterapp.ui.promos.details.PromoDetailsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_promo_list.rvPromoList
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListActivity : BaseActivity(), OnItemClickListener {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var mAdapter: PromoListAdapter

    @Inject
    lateinit var mFactory: PromoListViewModelFactory

    private lateinit var mViewModel: PromoListViewModel
    private var mPromoListData = ArrayList<PromoResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_promo_list)
        mViewModel = ViewModelProvider(this, mFactory).get(PromoListViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initListeners()
        loadPromos()
    }

    private fun initListeners() {
        mAdapter.setOnItemClickListener(this)
    }

    private fun initViews() {
        mAdapter.setData(mPromoListData)

        val layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(rvPromoList.context, layoutManager.orientation)

        rvPromoList.layoutManager = layoutManager
        rvPromoList.setHasFixedSize(true)
        rvPromoList.addItemDecoration(dividerItemDecoration)
        rvPromoList.adapter = mAdapter
    }

    private fun loadPromos() = launch {
        val promos = mViewModel.loadPromoList.await()
        promos.observe(this@PromoListActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    mPromoListData.addAll(it.data as PromoResponse)
                    mAdapter.notifyDataSetChanged()

                    Log.e("test>>>", mPromoListData.size.toString())
                }
                else -> {
                }
            }
        })
    }

    override fun onItemClicked(promoItem: PromoResponseItem) {
        val intent = Intent(this, PromoDetailsActivity::class.java)
        intent.putExtra(INTENT_EXTRAS_PROMO_ITEM, Gson().toJson(promoItem))
        startActivity(intent)
    }
}