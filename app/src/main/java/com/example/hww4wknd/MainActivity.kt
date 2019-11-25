package com.example.hww4wknd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom.*
import kotlinx.android.synthetic.main.fragment_mid.*
import kotlinx.android.synthetic.main.fragment_top.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentbottom: BottomFragment
    private lateinit var fragmentmid: MidFragment
    private lateinit var fragmenttop: TopFragment
    private lateinit var sys_reciever: BroadcastReceiver
    private lateinit var mail_reciever: BroadcastReceiver



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)

        }
        sys_reciever = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context, intent?.action, Toast.LENGTH_SHORT).show()
            }

        }

        registerReceiver(sys_reciever, filter)



        fragmentbottom = BottomFragment()
        fragmentmid = MidFragment()
        fragmenttop = TopFragment()

        openFragments()

        mail_reciever = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var intent = Intent("Blue's Clues")
                intent.putExtra("Mailbox", "We just got a letter!")
                sendBroadcast(intent)
            }

        }

        val second_filter = IntentFilter("MailTime")
        registerReceiver(mail_reciever, second_filter)


        broadcast_button.setOnClickListener {



            var firstintent = Intent("MailTime")
            firstintent.putExtra("Mailbox", "We just got a letter!")
            sendBroadcast(firstintent)





            broadcast_button.visibility = View.INVISIBLE
            


        }

    }





    private fun openFragments() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.top_frag, fragmenttop)
            .addToBackStack(fragmenttop.tag)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.bottom_frag, fragmentbottom )
            .addToBackStack(fragmentbottom.tag)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.mid_frag, fragmentmid)
            .addToBackStack(fragmentmid.tag)
            .commit()





    }


    override fun onBackPressed() {
        super.onBackPressed()

        supportFragmentManager.popBackStack()

    }


    override fun onDestroy(){

        unregisterReceiver(sys_reciever)
        unregisterReceiver(mail_reciever)
        super.onDestroy()

    }

}




