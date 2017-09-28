package com.linyuzai.kotlinextension.a

/**
 * Only for Android
 * @author linyuzai
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnlyForAndroid

/**
 * Request Application Context
 * @author linyuzai
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RequestContext

/**
 * This is an open api
 * @author linyuzai
 */
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class OpenApi
