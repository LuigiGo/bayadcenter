package com.androidsystems.bayadcenterapp.ui.promos.list

import android.os.Bundle
import com.androidsystems.bayadcenterapp.R.layout
import com.androidsystems.bayadcenterapp.core.base.BaseActivity
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS
import com.androidsystems.bayadcenterapp.data.network.base.NetworkServiceApi
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSourceImpl
import kotlinx.android.synthetic.main.activity_promo_list.textView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListActivity : BaseActivity() {

//    @Inject
//    lateinit var networkServiceApi: NetworkServiceApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_promo_list)
    }

    override fun onStart() {
        super.onStart()

//        val dataSource = PromosDataSourceImpl(networkServiceApi)

//        GlobalScope.launch(Dispatchers.Main) {
//            val promos = dataSource.getPromos()
//            dataSource.downloadedPromoList.observe(this@PromoListActivity, androidx.lifecycle.Observer {
//                when (it.status) {
//                    SUCCESS -> {
//                        textView.text = it.data.toString()
//                    }
//                    else -> {
//                    }
//                }
//            })
//
//        }
    }
}