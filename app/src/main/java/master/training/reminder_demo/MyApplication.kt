package master.training.reminder_demo

import android.app.Application
import master.training.reminder_demo.dependencyinjection.AndroidReminderProvider

class MyApplication : Application() {

    internal val dependencyProvider =
        AndroidReminderProvider(this)



    override fun onCreate() {
        super.onCreate()

        initializeRealm()

    }

    private fun initializeRealm() {

    }
}