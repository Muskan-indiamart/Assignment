package com.example.navigationdrawerapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_4.*

class Fragment4 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn =view.findViewById<Button>(R.id.btnUpdate)
        val btnd =view.findViewById<Button>(R.id.btnDelete)

        btn.setOnClickListener {


            val edtName = view.findViewById<EditText>(R.id.updateName)
            val edtPassword = view.findViewById<EditText>(R.id.updatePassword)


                val Name = edtName.text.toString()
                val Password = edtPassword.text.toString()

            val newName = newName.text.toString()
            val newPassword = newPassword.text.toString()

                val databaseHandler = getContext()?.let { Database(it)}

                if( Name.trim()!="" && Password.trim()!="" && newName.trim()!="" && newPassword.trim()!=""){
                    //calling the updateEmployee method of DatabaseHandler class to update record
                    val status = databaseHandler!!.updateUser(UserModel(Name,Password),UserModel(newName,newPassword))
                    if(status > -1){
                        Toast.makeText(context,"record update",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
                }

            }

        btnd.setOnClickListener {
            val dName = updateName.text.toString()
            val dPassword = updatePassword.text.toString()
            val databaseHandler = getContext()?.let { Database(it)}

            if( dName.trim()!="" && dPassword.trim()!="" ){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler!!.deleteUser(UserModel(dName,dPassword))
                if(status > -1){
                    Toast.makeText(context,"record deleted",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
            }

        }
        }
    }






