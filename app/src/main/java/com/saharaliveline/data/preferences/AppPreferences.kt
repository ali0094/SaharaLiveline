package com.saharaliveline.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(val sharedPreferences: SharedPreferences) {
    companion object {
        private const val IS_LOGIN_KEY = "IS_LOGIN"
        private const val USER_TYPE = "USER_TYPE"
        private const val DEVICE_ID_KEY = "DEVICE_ID"
        private const val FCM_TOKEN_KEY = "FCM_TOKEN"
        private const val AUTH_TOKEN = "AUTH_TOKEN"
        private const val PRIVACY_POLICY_KEY = "PRIVACY_POLICY"
        private const val TERMS_CONDITION_KEY = "TERMS_AND_CONDITION"
    }


    private var gson: Gson? = null

    fun getGsonObject(): Gson {
        if (gson != null)
            return gson as Gson
        gson = Gson()
        return gson as Gson
    }

    /**
     * Method to save user session
     * @param isLogin : Boolean
     */
    fun setIsLogin(isLogin: Boolean) {
        addBoolean(IS_LOGIN_KEY, isLogin)
    }

    /**
     * Method to get user session
     * @return true/false
     */
    fun isLoggedIn(): Boolean {
        return getBoolean(IS_LOGIN_KEY, false)
    }


    /**
     * Method to save user type
     * @param userType
     */
    fun saveUserType(userType: String) {
        addString(USER_TYPE, userType)
    }

    /**
     * Method to get user type
     * @return Flow<String>
     */
    fun getUserType(): String? {
        return getString(USER_TYPE, "")
    }


    /**
     * Method to save device id
     * @param deviceId
     */
    fun saveDeviceId(deviceId: String) {
        addString(DEVICE_ID_KEY, deviceId)
    }

    /**
     * Method to get device id
     */
    fun getDeviceId(): String? {
        return getString(DEVICE_ID_KEY, "")
    }


    /**
     * Method to save fcm token
     * @param fcmToken String
     */
    fun saveFcmToken(fcmToken: String) {
        addString(FCM_TOKEN_KEY, fcmToken)
    }

    /**
     * Method to get fcm token
     */
    fun getFcmToken(): String? {
        return getString(FCM_TOKEN_KEY, "")
    }


    /**
     * Method to auth fcm token
     * @param authToken String
     */
    fun saveAuthToken(authToken: String) {
        addString(AUTH_TOKEN, authToken)
    }

    /**
     * Method to get auth token
     */
    fun getAuthToken(): String? {
        return getString(AUTH_TOKEN, "")
    }


    /**
     * Method to save privacy policy url
     * @param privacyPolicyUrl String
     */
    fun savePrivacyPolicyUrl(privacyPolicyUrl: String) {
        addString(PRIVACY_POLICY_KEY, privacyPolicyUrl)
    }

    /**
     * Method to get privacy policy url
     * @return Flow<String>
     */
    fun getPrivacyPolicyUrl(): String? {
        return getString(PRIVACY_POLICY_KEY, "")
    }

    /**
     * Method to save terms and conditions url
     * @param termAndConditionsUrl String
     */
    fun saveTermsAndConditionsUrl(termAndConditionsUrl: String) {
        addString(TERMS_CONDITION_KEY, termAndConditionsUrl)
    }

    /**
     * Method to get terms and conditions url
     * @return Flow<String>
     */
    fun getTermsAndConditionsUrl(): String? {
        return getString(TERMS_CONDITION_KEY, "")
    }


//    Generic methods for manipulating shared prefs

    fun containKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    private fun addBoolean(key: String, value: Boolean) {
        getSharedPrefsEditor().putBoolean(key, value).apply()
    }

    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    private fun addObject(key: String, `object`: Any) {
        addString(key, getGsonObject().toJson(`object`))
    }

    private fun addString(key: String, value: String) {
        getSharedPrefsEditor().putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    private fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    private fun putInt(key: String, value: Int) {
        getSharedPrefsEditor().putInt(key, value).apply()
    }

    private fun putLong(key: String, value: Long) {
        getSharedPrefsEditor().putLong(key, value).apply()
    }

    private fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    private fun putFloat(key: String, value: Float) {
        getSharedPrefsEditor().putFloat(key, value).apply()
    }

    private fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    private fun remove(key: String) {
        getSharedPrefsEditor().remove(key).apply()
    }


    private fun getSharedPrefsEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
}