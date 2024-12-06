package mak.app.anikloud.core.common.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import mak.app.anikloud.core.common.util.Dispatcher

internal abstract class BaseViewModel(
    dispatcher: Dispatcher
): ViewModel() {
    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * Handle exception to display a message instead of crashing
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }
    /**
     * This is the main scope for all coroutines launched by this ViewModel.
     * Since we pass [viewModelJob], you can cancel all coroutines
     * launched by uiScope by calling [viewModelJob.cancel()]
     */
    protected val uiScope: CoroutineScope = CoroutineScope(dispatcher.main + viewModelJob + exceptionHandler)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    open fun handleError(exception: Throwable) {}
}