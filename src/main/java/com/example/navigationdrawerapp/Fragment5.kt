package com.example.navigationdrawerapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment5.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment5 : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtview1 = view.findViewById<EditText>(R.id.usernames)
        val txtview2 = view.findViewById<EditText>(R.id.passwords)

        val args = this.arguments
        Log.d("args", "$args, $arguments")

        val inputData1 = args?.getString("UserName")
        Log.d("Username", "$inputData1")

        txtview1.setText(inputData1)


        val inputData2 = args?.getString("Password")

        txtview2.setText(inputData2)


    }
    }


