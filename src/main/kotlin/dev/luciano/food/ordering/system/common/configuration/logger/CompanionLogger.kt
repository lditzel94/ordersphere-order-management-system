package dev.luciano.food.ordering.system.common.configuration.logger

import arrow.core.Either
import org.slf4j.MDC
import reactor.util.Logger
import reactor.util.Loggers

abstract class CompanionLogger {
    val log: Logger by lazy { Loggers.getLogger(javaClass.enclosingClass) }
    private val folder: String by lazy { javaClass.enclosingClass.packageName.substringAfterLast('.').uppercase() }

    protected fun <T> T.log(block: Logger.(T) -> Unit): T =
        also {
            MDC.put("folder", folder)
            MDC.put("thread", Thread.currentThread().toString())
            block(log, this)
            MDC.remove("folder")
        }

    protected suspend fun <T> T.suspendLog(block: suspend Logger.(T) -> Unit): T =
        also {
            MDC.put("folder", folder)
            MDC.put("thread", Thread.currentThread().toString())
            block(log, this)
            MDC.remove("folder")
        }

    protected suspend fun <L, R> Either<L, R>.logRight(right: suspend Logger.(R) -> Unit): Either<L, R> =
        also {
            fold({}, { r -> suspendLog { right(r) } })
        }

    protected suspend fun <L, R> Either<L, R>.logLeft(left: suspend Logger.(L) -> Unit): Either<L, R> =
        also {
            fold({ l -> suspendLog { left(l) } }, { })
        }

    protected suspend fun <L, R> Either<L, R>.logEither(
        left: suspend Logger.(L) -> Unit,
        right: suspend Logger.(R) -> Unit,
    ): Either<L, R> =
        also {
            fold({ l -> suspendLog { left(l) } }, { r -> suspendLog { right(r) } })
        }

    protected suspend fun <T, R> T.logged(
        description: String? = null,
        leftLog: String,
        rightLog: String,
        action: suspend T.() -> Either<Error, R>,
    ) = suspendLog { info("$description: {}", it) }
        .action()
        .logEither(
            { error("$leftLog: {}", it.message) },
            { info("$rightLog: {}", it) },
        )
}