package master.training.reminder_demo.movementlist

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import master.training.reminder_demo.TAG
import master.training.reminder_demo.other.BaseLogic
import master.training.reminder_demo.dependencyinjection.AndroidMovementProvider
import master.training.reminder_demo.other.ERROR_GENERIC
import master.training.reminder_demo.other.ResultWrapper
import kotlin.coroutines.CoroutineContext

class MovementListLogic(
    private val view: MovementListContract.View,
    private val provider: AndroidMovementProvider,
    dispatcher: CoroutineDispatcher

) : BaseLogic<MovementListEvent>(dispatcher) {


    override val coroutineContext: CoroutineContext
        get() = main + jobTracker

    override fun handleEvent(eventType: MovementListEvent) {
        when (eventType) {
            is MovementListEvent.OnStart -> getMovementsData()
            is MovementListEvent.OnSettingsClick -> view.startSettingsView()
            is MovementListEvent.OnRemindersClick -> view.startRemindersView()
            is MovementListEvent.OnMovementClick -> view.startMovementView(eventType.movementId)
            is MovementListEvent.OnStop -> jobTracker.cancel()
        }

    }

    /*
    *
    * getMovementsData */
    private fun getMovementsData() = launch {

        val result = provider.movementRepository.getMovements()
        when (result) {
            is ResultWrapper.Success -> view.setMovementListData(result.value)
            is ResultWrapper.Error -> {
                Log.d(TAG, result.error.toString())
                view.showMessage(ERROR_GENERIC)
                view.startRemindersView()
            }
        }
    }
}