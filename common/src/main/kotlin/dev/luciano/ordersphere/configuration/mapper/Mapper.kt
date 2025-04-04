package dev.luciano.ordersphere.configuration.mapper

import arrow.core.Either

fun interface Mapper<in From, out To> {
    fun map(from: From): To
}

fun <From, To> Mapper<From, To>.map(list: List<From>): List<To> = list.map { map(it) }

fun <Error, From, To> Mapper<From, To>.map(either: Either<Error, From>): Either<Error, To> = either.map { map(it) }