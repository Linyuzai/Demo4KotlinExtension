package com.linyuzai.kotlinextension

import android.app.*
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.PowerManager
import android.os.Vibrator
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Context.windowManager(): WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.layoutInflater(): LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun Context.activityManager(): ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

fun Context.powerManager(): PowerManager = getSystemService(Context.POWER_SERVICE) as PowerManager

fun Context.alarmManager(): AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

fun Context.notificationManager(): NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.keyguardManager(): KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

fun Context.locationManager(): LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun Context.searchManager(): SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

fun Context.connectivityManager(): ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.wifiManager(): WifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

fun Context.telephonyManager(): TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

fun Context.inputMethodManager(): InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.uiModeManager(): UiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

fun Context.downloadManager(): DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

fun Context.vibrator(): Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


