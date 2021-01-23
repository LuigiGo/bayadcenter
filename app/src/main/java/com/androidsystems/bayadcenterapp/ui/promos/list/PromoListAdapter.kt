package com.androidsystems.bayadcenterapp.ui.promos.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidsystems.bayadcenterapp.R
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import java.util.ArrayList
import javax.inject.Inject

interface OnItemClickListener {
    fun onItemClicked(promoItem: PromoItem)
    fun onItemLongClicked(promoItem: PromoItem)
}

class PromoListAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<PromoListAdapter.ViewHolder>() {

    private var mData = ArrayList<PromoItem>()
    private lateinit var mListener: OnItemClickListener

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val ivPromoStatus = v.findViewById<ImageView>(R.id.ivPromoStatus)
        var tvPromoName = v.findViewById<TextView>(R.id.tvPromoName)
        val tvPromoDetails = v.findViewById<TextView>(R.id.tvPromoDetails)
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.adapter_promo_item, null, false)
        return ViewHolder(v)
    }

    fun setData(data: ArrayList<PromoItem>) {
        mData = data
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promoItem = mData[position]
        val isPromoUnread = (promoItem.read != 1)

        holder.ivPromoStatus.isEnabled = isPromoUnread
        holder.ivPromoStatus.visibility = if (isPromoUnread) View.VISIBLE else View.GONE
        holder.tvPromoName.text = promoItem.name
        holder.tvPromoDetails.text = promoItem.details

        holder.itemView.setOnClickListener {
            mListener.onItemClicked(promoItem)
        }

        holder.itemView.setOnLongClickListener {
            mListener.onItemLongClicked(promoItem)
            return@setOnLongClickListener true
        }
    }
}