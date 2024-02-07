package com.example.karyawanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karyawanapp.adapters.KaryawanAdapter
import com.example.karyawanapp.databinding.ActivityMainBinding
import com.example.karyawanapp.databinding.ConfirmDialogBinding
import com.example.karyawanapp.databinding.CustomDialogBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

//    Inisialisasi dan Memanggil Main ViewModel
    private val mainViewModel: MainViewModel by viewModels()

//    Memanggil id pada activity_main menggunakan ViewBinding
    private var bindingMainActivity: ActivityMainBinding? = null
    private val binding get() = bindingMainActivity!!

//    Inisialisasi Mahasiswa Adapter
    private lateinit var karyawanAdapter: KaryawanAdapter

//      Dialog Builder update dan delete data
    var dialogBuilder: AlertDialog? = null

    private var dialogBinding: CustomDialogBinding? = null
    private val bindingDialog get() = dialogBinding!!

//    Dialog Bulider confirm delete
    var confirmDialogBuilder: AlertDialog? = null

    private var confirmDialogBinding: ConfirmDialogBinding? = null
    private val bindingConfirmDialog get() = confirmDialogBinding!!

//    variabel yang akan dipanggil ke update form
    lateinit var  mNik : String
    lateinit var  mNama : String
    lateinit var  mAlamat : String
    lateinit var  mNoHp : String

//    variabel untuk mengganti title saat berada pada halaman edit
    val titleDialog: String = "Edit Data Karyawan"

//    variabel untuk mengganti icon, textview, dan button delete menjadi button update
    val tvConfirm: String = "Do you really want to update these record? These proccess cannot be undone."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        karyawanAdapter = KaryawanAdapter()

        mainViewModel.karyawan.observe(this, Observer {

            karyawanAdapter.setData(it)
        })

        mainViewModel.actionKaryawan.observe(this, Observer {
            mainViewModel.getAllKaryawan()
        })

//        saat floating action button diklik akan menampilkan showCustomDialog menambah kan flag baru
        binding.floatingActionButton.setOnClickListener {
            showCustomDialog("New")
        }

//        saat delete button diklik maka akan muncul confirm dialog

        mainViewModel.snackbarText.observe(this, {
            it.getContentIfNotHandled()?.let {
                Snackbar.make(window.decorView.rootView, it, Snackbar.LENGTH_SHORT).show()
            }
        })

//        ketika mahasiswa adapter diklik, maka akan muncul alert yang berisi data yang akan dieadit berdasarkan unik npm
        karyawanAdapter.onItemClick = {

            mNik = it.nik
            mNama = it.nama
            mAlamat = it.alamat
            mNoHp = it.no_hp

            showCustomDialog("Edit")

            bindingDialog.tvTitleDialog.text = "$titleDialog"
        }

        with(binding.rvKaryawan) {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = karyawanAdapter
        }

    }

    fun showCustomDialog(flag: String) {

        dialogBuilder = AlertDialog.Builder(this).create()
        dialogBinding = CustomDialogBinding.inflate(layoutInflater)

        if (flag.equals("New")){

            bindingDialog.btnInput.visibility = View.VISIBLE
            bindingDialog.btnUpdate.visibility = View.GONE
            bindingDialog.btnDelete.visibility = View.GONE

        } else {

            bindingDialog.edtNIK.setText(mNik)
            bindingDialog.edtNama.setText(mNama)
            bindingDialog.edtAlamat.setText(mAlamat)
            bindingDialog.edtNoHp.setText(mNoHp)

            bindingDialog.btnInput.visibility = View.GONE
            bindingDialog.btnUpdate.visibility = View.VISIBLE
            bindingDialog.btnDelete.visibility = View.VISIBLE

            bindingDialog.edtNIK.isFocusable = false

        }

        dialogBinding?.btnInput?.setOnClickListener {

            val nik = bindingDialog.edtNIK.text.toString()
            val nama = bindingDialog.edtNama.text.toString()
            val alamat = bindingDialog.edtAlamat.text.toString()
            val no_hp = bindingDialog.edtNoHp.text.toString()

            if(nik.isNotEmpty() && nama.isNotEmpty() && alamat.isNotEmpty() && no_hp.isNotEmpty()) {

                mainViewModel.insertKaryawan(nik, nama, alamat, no_hp)

                dialogBuilder?.dismiss()

            } else {

                Toast.makeText(this, "Silahkan isi data terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBinding?.btnUpdate?.setOnClickListener {

            showConfirmDialog("Update")

            confirmDialogBinding?.tvConfirm?.text = "$tvConfirm"

            dialogBuilder?.dismiss()
        }

        dialogBinding?.btnDelete?.setOnClickListener {

            showConfirmDialog("Delete")
            dialogBuilder?.dismiss()
        }

        dialogBuilder?.setView(bindingDialog.root)
        dialogBuilder?.show()

    }

    fun showConfirmDialog(flag: String) {

        confirmDialogBuilder = AlertDialog.Builder(this).create()
        confirmDialogBinding = ConfirmDialogBinding.inflate(layoutInflater)

        if(flag.equals("Delete")) {

            bindingConfirmDialog.btnConfirmUpdate.visibility = View.GONE

        } else{

            bindingConfirmDialog.btnConfirmDelete.visibility = View.GONE

            bindingConfirmDialog.icConfirm.setImageResource(R.drawable.seru)
        }

        confirmDialogBinding?.btnConfirmDelete?.setOnClickListener {

            val nik = bindingDialog.edtNIK.text.toString()

            mainViewModel.deleteKaryawan(nik)

            confirmDialogBuilder?.dismiss()

        }

        confirmDialogBinding?.btnConfirmUpdate?.setOnClickListener {

            val nik = bindingDialog.edtNIK.text.toString()
            val nama = bindingDialog.edtNama.text.toString()
            val alamat = bindingDialog.edtAlamat.text.toString()
            val no_hp = bindingDialog.edtNoHp.text.toString()

            mainViewModel.updateKaryawan(nik, nama, alamat, no_hp)

            confirmDialogBuilder?.dismiss()
        }

        confirmDialogBinding?.btnConfirmCencel?.setOnClickListener {

            confirmDialogBuilder?.dismiss()
        }

        confirmDialogBuilder?.setView(bindingConfirmDialog.root)
        confirmDialogBuilder?.show()

    }

}