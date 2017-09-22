package com.linyuzai.kotlinextension.c

import android.app.*
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.PowerManager
import android.os.Vibrator
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.linyuzai.kotlinextension.*
import com.linyuzai.kotlinextension.a.RequestContext

/**
 * Created by linyuzai on 2017/9/8.
 * @author linyuzai
 */
internal object KService : IService {
    override fun window(): WindowManager = Ex.context!!.windowManager()

    override fun inflater(): LayoutInflater = Ex.context!!.layoutInflater()

    override fun activity(): ActivityManager = Ex.context!!.activityManager()

    override fun power(): PowerManager = Ex.context!!.powerManager()

    override fun alarm(): AlarmManager = Ex.context!!.alarmManager()

    override fun notification(): NotificationManager = Ex.context!!.notificationManager()

    override fun keyguard(): KeyguardManager = Ex.context!!.keyguardManager()

    override fun location(): LocationManager = Ex.context!!.locationManager()

    override fun search(): SearchManager = Ex.context!!.searchManager()

    override fun connection(): ConnectivityManager = Ex.context!!.connectivityManager()

    override fun wifi(): WifiManager = Ex.context!!.wifiManager()

    override fun telephony(): TelephonyManager = Ex.context!!.telephonyManager()

    override fun inputMethod(): InputMethodManager = Ex.context!!.inputMethodManager()

    override fun uiMode(): UiModeManager = Ex.context!!.uiModeManager()

    override fun download(): DownloadManager = Ex.context!!.downloadManager()

    override fun vibrator(): Vibrator = Ex.context!!.vibrator()

}

@RequestContext
interface IService {
    fun vibrator(): Vibrator

    fun window(): WindowManager

    fun inflater(): LayoutInflater

    fun activity(): ActivityManager

    fun power(): PowerManager

    fun alarm(): AlarmManager

    fun notification(): NotificationManager

    fun keyguard(): KeyguardManager

    fun location(): LocationManager

    fun search(): SearchManager

    fun connection(): ConnectivityManager

    fun wifi(): WifiManager

    fun telephony(): TelephonyManager

    fun inputMethod(): InputMethodManager

    fun uiMode(): UiModeManager

    fun download(): DownloadManager
}