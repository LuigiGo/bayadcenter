package com.androidsystems.bayadcenterapp.ui.promos.details

import android.os.Bundle
import com.androidsystems.bayadcenterapp.R
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.INTENT_EXTRAS_PROMO_ITEM
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_promo_details.ivPromoBanner
import kotlinx.android.synthetic.main.activity_promo_details.tvPromoName
import kotlinx.android.synthetic.main.adapter_promo_item.tvPromoDetails

class PromoDetailsActivity : BaseActivity() {

    private lateinit var mPromoItem: PromoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_details)
        initData()
    }

    private fun initData() {
        val promoItemExtras = intent.getStringExtra(INTENT_EXTRAS_PROMO_ITEM)
        mPromoItem = Gson().fromJson(promoItemExtras, PromoItem::class.java)
    }

    override fun onStart() {
        super.onStart()
        loadPromoDetails()
    }

    private fun loadPromoDetails() {
        Glide.with(this).load(mPromoItem.imageUrl).into(ivPromoBanner)
        tvPromoName.text = mPromoItem.name
        tvPromoDetails.text = mPromoItem.details
    }
}