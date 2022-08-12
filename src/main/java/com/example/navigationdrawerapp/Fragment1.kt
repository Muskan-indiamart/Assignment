package com.example.navigationdrawerapp

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager

import android.content.pm.PackageManager.PERMISSION_GRANTED


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat


import androidx.core.view.isGone
import androidx.core.view.isInvisible

import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1 : Fragment() {
    companion object {

        private const val STORAGE_PERMISSION_CODE = 101
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_1, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storage: Button
        = view.findViewById(R.id.storage)
        storage.setOnClickListener {checkPermission(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,"Storage",
            STORAGE_PERMISSION_CODE)
        }



        val add_fab = view.findViewById<FloatingActionButton>(R.id.add_fab)
        val edit_fab=view.findViewById<FloatingActionButton>(R.id.edit)
        val edit_fab_text =view.findViewById<TextView>(R.id.edit_text)



        add_fab.setOnClickListener{
            edit_fab.show()


        }
        edit_fab.setOnClickListener{
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, Fragment4())
            transaction.commit()

        }

    }
    private fun checkPermission(permission: String, name: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {

                ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(activity, "Permission already granted", Toast.LENGTH_SHORT)
                        .show()

                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(
                    permission,
                    name,
                    requestCode
                )
                else -> ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(permission),
                    requestCode
                )
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
                Toast.makeText(activity, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
        if (requestCode == STORAGE_PERMISSION_CODE) {
            innerCheck(name = "Storage")

        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setMessage("Permission to access your $name is required to use the app")
            setTitle("Permission required")
            setPositiveButton("Okay") { dialog, which ->
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(permission),
                    requestCode
                )
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}




