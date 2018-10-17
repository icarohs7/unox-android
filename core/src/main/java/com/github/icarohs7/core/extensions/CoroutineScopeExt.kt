package com.github.icarohs7.core.extensions

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job

/**
 * Await a list of jobs, suspending until are all completed
 */
suspend inline fun CoroutineScope.awaitJobs(builder: MutableList<Job>.() -> Unit) {
    val jobs = mutableListOf<Job>()
    jobs.builder()
    jobs.forEach { it.join() }
}