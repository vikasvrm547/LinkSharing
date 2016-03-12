package com.tothenew.util

import com.tothenew.constants.Constants
import org.apache.commons.lang.RandomStringUtils

class Utility {
    static String getRandomPassword() {
        RandomStringUtils randomPassword = new RandomStringUtils();
        return randomPassword.randomAlphanumeric(Constants.PASSWORD_LENGTH)
    }
}
