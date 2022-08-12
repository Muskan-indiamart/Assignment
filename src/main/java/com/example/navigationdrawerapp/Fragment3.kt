package com.example.navigationdrawerapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_3.*


class Fragment3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


return inflater.inflate(R.layout.fragment_3, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = this
//        val db = getContext()?.let { Database(it)}
//        val view= inflater.inflate(R.layout.fragment_3, container, false)


        val btn =view.findViewById<Button>(R.id.btnLogin)
        val ibtn =view.findViewById<Button>(R.id.add_user)

        ibtn.setOnClickListener{
            val uname = txt_Username.text.toString()
            val pword=txt_Password.text.toString()

            val databaseHandler = getContext()?.let { Database(it)}
            if (uname.trim()!=""&& pword.trim()!="")
             {


                 val status = databaseHandler?.insertData(UserModel(uname, pword))
                 if (status != null) {
                     if(status > -1){
                         Toast.makeText(getContext(),"record save",Toast.LENGTH_LONG).show()
                         Log.d("Data","$uname")

                     } else {
                         Toast.makeText(getContext(), "save unsuccessful", Toast.LENGTH_SHORT).show()
                     }
                     txt_Username.setText("")
                     txt_Password.setText("")
                 }

             }
            else{
                Toast.makeText(getContext(),"id or name or email cannot be blank",Toast.LENGTH_LONG).show()

            }
    }
        btn.setOnClickListener{


            if(txt_Username.text.isNotEmpty() && txt_Password.text.isNotEmpty()){
                val db = getContext()?.let { Database(it) }

                if(db!!.Login(txt_Username.text,txt_Password.text)){
                    Toast.makeText(getContext(),"Successfully Logged In", Toast.LENGTH_LONG).show();


                    val uName=txt_Username.text.toString()
                    val pWord=txt_Password.text.toString()
                    Log.d("Data", uName.toString())





                    val args = Bundle()
                    args.putString("UserName",uName)
                    args.putString("Password",pWord)

                    val fragment5 = Fragment5()
                    fragment5.arguments= args
                    Log.d("Arguments","$args")

                    setToolbarTitle("Fragment 5")
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_fragment, fragment5)
                    transaction.commit()
                }
                else{
                   Toast.makeText(getContext(),"Invalid Username/Password", Toast.LENGTH_LONG).show();
                }


            }
        }


    }

    private fun setToolbarTitle(s: String) {
        (activity as AppCompatActivity).supportActionBar?.title = s
    }
}









