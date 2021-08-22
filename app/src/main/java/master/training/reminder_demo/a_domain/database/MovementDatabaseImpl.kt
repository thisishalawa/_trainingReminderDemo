package master.training.reminder_demo.a_domain.database

import android.content.res.AssetManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master.training.reminder_demo.a_domain.model.Movement
import master.training.reminder_demo.a_domain.repository.IMovementRepository
import master.training.reminder_demo.other.MovementRepositoryException
import master.training.reminder_demo.other.ResultWrapper

class MovementDatabaseImpl(
    private val assets: AssetManager
) : IMovementRepository {

    override suspend fun getMovements(): ResultWrapper<Exception, List<Movement>> =
        withContext(Dispatchers.IO) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            val jsonString = assets.open("movements.json")
                .bufferedReader()
                .use {
                    it.readText()
                }

            val type = Types.newParameterizedType(List::class.java, Movement::class.java)
            val adapter = moshi.adapter<List<Movement>>(type)
            ResultWrapper.build {
                adapter.fromJson(jsonString) ?: throw MovementRepositoryException
            }

        }




}