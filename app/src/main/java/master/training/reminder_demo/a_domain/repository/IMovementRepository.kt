package master.training.reminder_demo.a_domain.repository

import master.training.reminder_demo.a_domain.model.Movement
import master.training.reminder_demo.other.ResultWrapper

interface IMovementRepository {

    suspend fun getMovements(): ResultWrapper<Exception, List<Movement>>


}