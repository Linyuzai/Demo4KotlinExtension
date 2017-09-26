package com.linyuzai.kotlinextension.a

/**
 * Only for Android
 * @author linyuzai
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class OnlyForAndroid

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RequestContext

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class OpenApi
