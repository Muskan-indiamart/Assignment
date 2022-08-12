package com.example.navigationdrawerapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.list_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Fragment2 : Fragment(), ListBtnClicked {
    lateinit var adapter:ListAdapter

    companion object {

        private const val REQUEST_CALL= 100
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


//        val view : View=inflater.inflate(R.layout.fragment_2, container, false)


return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val recyclerView =view.findViewById<RecyclerView>(R.id.recyclerView)

        getUserData()
    }
    private fun getUserData()
    {
        val data : Call<AllUsers> = Data.dataInstance.getData()
        data.enqueue(object : Callback<AllUsers>{
            override fun onFailure(call: Call<AllUsers>, t: Throwable) {
                Log.d( "User", "Error in fetching Details",t)


            }

            override fun onResponse(call: Call<AllUsers>, response: Response<AllUsers>) {
                val user=response.body()
                if(user!=null)
                {
                    Log.d("User", data.toString())

                    val adapter : ListAdapter = ListAdapter(this@Fragment2,requireContext(), user.users)
                    recyclerView.adapter=adapter
                    recyclerView.layoutManager=LinearLayoutManager(activity)
                }

            }
        })
    }





    override fun onBtnClicked(userInfo: UserInfo) {

        val number=userInfo.phone.toString().trim()
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ Uri.encode(number)))
            startActivity(intent)
        }
        else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_CALL)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                    val intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ Uri.encode(phone.toString())))
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Call Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }}



