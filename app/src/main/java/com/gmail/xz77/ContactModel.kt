package com.gmail.xz77

import androidx.annotation.DrawableRes

data class ContactModel (
    val contactName: String,
    val firstPhoneNumber: String,
    val secondPhoneNumber: String,
    val firstEmail: String,
    val secondEmail: String,
    val contactDescription: String,
    @DrawableRes val photoResId: Int,
)