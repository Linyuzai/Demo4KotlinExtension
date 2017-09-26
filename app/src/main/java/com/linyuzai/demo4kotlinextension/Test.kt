package com.linyuzai.demo4kotlinextension

/**
 * Created by linyuzai on 2017/9/22.
 * @author linyuzai
 */
fun main(args: Array<String>) {
    val t: String = "123"
    val obj: Any = t
    when (obj) {
        is Int -> print("Int")
        is Long -> print("Long")
        is String -> print("String")
        is Boolean -> print("Boolean")
    }
}