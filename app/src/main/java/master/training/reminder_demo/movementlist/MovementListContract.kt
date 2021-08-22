package master.training.reminder_demo.movementlist

import master.training.reminder_demo.a_domain.model.Movement

interface MovementListContract {

    interface View {
        fun setMovementListData(movementListData: List<Movement>)

        fun startMovementView(movementId: String)

        fun startSettingsView()

        fun showMessage(msg: String)

        fun startRemindersView()
    }
}

sealed class MovementListEvent {
    object OnSettingsClick : MovementListEvent()
    object OnRemindersClick : MovementListEvent()
    data class OnMovementClick(val movementId: String) : MovementListEvent()
    object OnStart : MovementListEvent()
    object OnStop : MovementListEvent()
}