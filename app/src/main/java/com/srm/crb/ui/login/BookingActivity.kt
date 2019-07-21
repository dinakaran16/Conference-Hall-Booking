package com.srm.crb.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srm.crb.R

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookings)
        title = resources.getString(R.string.booking_details)
    }
}