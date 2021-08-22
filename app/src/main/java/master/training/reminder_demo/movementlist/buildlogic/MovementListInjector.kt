package master.training.reminder_demo.movementlist.buildlogic

import android.app.Application
import kotlinx.coroutines.Dispatchers
import master.training.reminder_demo.dependencyinjection.AndroidMovementProvider
import master.training.reminder_demo.movementlist.MovementListFragment
import master.training.reminder_demo.movementlist.MovementListLogic

object MovementListInjector {
    internal fun provideLogic(
        application: Application,
        view: MovementListFragment

    ): MovementListLogic {
        return MovementListLogic(
            view,
            AndroidMovementProvider(application),
            Dispatchers.Main
        )
    }
}