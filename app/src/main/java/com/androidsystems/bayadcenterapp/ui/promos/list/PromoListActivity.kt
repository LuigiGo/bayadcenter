package com.androidsystems.bayadcenterapp.ui.promos.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.androidsystems.bayadcenterapp.R.layout
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.INTENT_EXTRAS_PROMO_ITEM
import com.androidsystems.bayadcenterapp.core.utils.Status.ERROR
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import com.androidsystems.bayadcenterapp.ui.promos.details.PromoDetailsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_promo_list.rvPromoList
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListActivity : BaseActivity(), OnItemClickListener {

    @Inject
    lateinit var factory: PromoListViewModelFactory

    lateinit var mViewModel: PromoListViewModel

    @Inject
    lateinit var mAdapter: PromoListAdapter

    private var mPromoListData = ArrayList<PromoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_promo_list)
        mViewModel = ViewModelProvider(this, factory).get(PromoListViewModel::class.java)

        initViews()
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        mViewModel.getDownloadedPromos().observe(this@PromoListActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    mPromoListData.clear()
                    mPromoListData.addAll(it.data as PromoResponse)
                    mAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
        })
        mViewModel.getUpdatedPromoItem().observe(this@PromoListActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    val intent = Intent(this, PromoDetailsActivity::class.java)
                    intent.putExtra(INTENT_EXTRAS_PROMO_ITEM, Gson().toJson(it.data))
                    startActivity(intent)
                }
                ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
        })

        mViewModel.getDeletedPromoItem().observe(this@PromoListActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    loadPromos()
                }
                ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    loadPromos()
                }
                else -> {

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
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
        mViewModel.getPromoList()
    }

    override fun onItemClicked(promoItem: PromoItem) {
        promoItem.read = 1
        mViewModel.updatePromo(promoItem)
    }

    override fun onItemLongClicked(promoItem: PromoItem) {
        mViewModel.deletePromo(promoItem)
    }
}