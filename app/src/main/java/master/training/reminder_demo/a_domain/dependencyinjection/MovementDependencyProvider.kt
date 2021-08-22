package master.training.reminder_demo.a_domain.dependencyinjection

import master.training.reminder_demo.a_domain.api.IMovementAPI
import master.training.reminder_demo.a_domain.repository.IMovementRepository

interface MovementDependencyProvider {
    val movementRepository: IMovementRepository
    val movementAPI: IMovementAPI
}