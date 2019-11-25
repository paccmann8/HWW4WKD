package com.example.hww4wknd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mid.*

/**
 * A simple [Fragment] subclass.
 */
class MidFragment : Fragment() {
    lateinit var mid_reciever: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_mid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Glide.with(this)
            //.load(getString(R.string.logo3))
           // .into(right_image)


    }
    override fun onStart() {
        super.onStart()
        mid_reciever = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var TheLetter = intent?.getStringExtra("Mailbox")
                textView_mid.text = TheLetter


            }

        }


        val filter = IntentFilter("Blue's Clues")



        val main = activity as MainActivity?

        main?.registerReceiver(mid_reciever, filter)

    }


    override fun onDestroy() {
        val main = activity as MainActivity?

        main?.unregisterReceiver(mid_reciever)

        super.onDestroy()

    }

}