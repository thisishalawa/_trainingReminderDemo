package master.training.reminder_demo.dependencyinjection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import master.training.reminder_demo.MyApplication
import master.training.reminder_demo.a_domain.api.IMovementAPI
import master.training.reminder_demo.a_domain.api.movementapi.MovementAPI
import master.training.reminder_demo.a_domain.database.MovementDatabaseImpl
import master.training.reminder_demo.a_domain.dependencyinjection.MovementDependencyProvider
import master.training.reminder_demo.a_domain.repository.IMovementRepository

class AndroidMovementProvider(
    application: Application
) : AndroidViewModel(application),
    MovementDependencyProvider {

    override val movementRepository: IMovementRepository
        get() = MovementDatabaseImpl(getApplication<MyApplication>().assets)


    override val movementAPI: IMovementAPI
        get() = MovementAPI


}