package com.srm.crb.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val displayName: String,
        val isAdmin: Boolean
        //... other data fields that may be accessible to the UI
)
