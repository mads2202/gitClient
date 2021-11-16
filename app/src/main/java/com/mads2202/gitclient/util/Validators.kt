package com.mads2202.gitclient.util

import com.mads2202.gitclient.ui.ViewState
import java.util.regex.Matcher
import java.util.regex.Pattern


fun isValidString(pattern: Pattern, str: String): Boolean {
    val matcher: Matcher = pattern.matcher(str)
    return matcher.find()
}
