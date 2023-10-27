package com.example.basekotlinproject.utils.extention

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * Collect with lifecycle
 *
 * @param T
 * @param lifecycleOwner
 * @param minActiveState
 * @param action
 * @receiver
 * @return
 */
inline fun <reified T> Flow<T>.launchAndCollectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit = {}
): Job = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
        collect(action)
    }
}


inline fun <reified T> Flow<T>.launchAndCollectEachIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit = {}
): Job = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
        onEach(action)
    }
}
