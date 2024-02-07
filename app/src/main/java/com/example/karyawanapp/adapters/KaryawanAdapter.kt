package com.example.karyawanapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.karyawanapp.R
import com.example.karyawanapp.databinding.ItemKaryawanBinding
import com.example.karyawanapp.model.DataKaryawan

class KaryawanAdapter : RecyclerView.Adapter<KaryawanAdapter.ViewHolder>() {

    private var listDataMhs = ArrayList<DataKaryawan>()

    var onItemClick: ((DataKaryawan)-> Unit)? = null

    fun setData(mListDataMhs: List<DataKaryawan>?) {

        listDataMhs.clear()
        notifyDataSetChanged()

        if (mListDataMhs == null) return
        listDataMhs.addAll(mListDataMhs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_karyawan, parent, false))

    override fun onBindViewHolder(holder: KaryawanAdapter.ViewHolder, position: Int) {
        val data = listDataMhs[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listDataMhs.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        private val binding = ItemKaryawanBinding.bind(itemView)

        fun bind(data: DataKaryawan) {
            with(binding) {

                tvListNIK.text = data.nik
                tvListNama.text = data.nama
                tvListAlamat.text = data.alamat
                tvListNoHp.text = data.no_hp
            }

        }

        init {
            binding.cardList.setOnLongClickListener {

                onItemClick?.invoke(listDataMhs[adapterPosition])

                return@setOnLongClickListener true
            }
        }
    }
}