package com.srm.crb.ui.login

import com.srm.crb.db.User

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val displayName: String,
        val isAdmin: Boolean,
        val user: User?
        //... other data fields that may be accessible to the UI
)
