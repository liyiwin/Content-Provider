package com.example.contentprovider

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission ()

        query()

        setUI()

        setActions ()

    }

    fun setPermission (){

        val permissionList = arrayListOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.WRITE_CONTACTS)

        val requestList = arrayListOf<String>()


        for (i in permissionList.indices){

           if (ActivityCompat.checkSelfPermission(this, permissionList[i]) != PackageManager.PERMISSION_GRANTED) requestList.add(permissionList[i])


        }

        val array = arrayOfNulls<String>(requestList.size)

        if (requestList.isNotEmpty()) ActivityCompat.requestPermissions(this, requestList.toArray(array), 0)


    }

    fun setUI(){


    }

    fun setActions (){


    }

    fun query(){

        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)

        while(cursor!!.moveToNext()){

            val id = cursor.getInt( cursor.getColumnIndex(ContactsContract.Contacts._ID)).toString()

            val name = cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

            Log.d("RECORD", id + "/" + name)

        }


    }


}
