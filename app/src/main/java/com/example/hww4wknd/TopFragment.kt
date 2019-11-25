package com.example.hww4wknd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_top.*




/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {

    lateinit var top_reciever: BroadcastReceiver
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)










    }


    override fun onStart() {
        super.onStart()
        top_reciever = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var TheLetter = intent?.getStringExtra("Mailbox")
                textView_top.text = TheLetter


            }

        }


        val filter = IntentFilter("Blue's Clues")



        val main = activity as MainActivity?

        main?.registerReceiver(top_reciever, filter)

    }

    override fun onDestroy() {

        val main = activity as MainActivity?

        main?.unregisterReceiver(top_reciever)
        super.onDestroy()

    }


}