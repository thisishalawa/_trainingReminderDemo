package master.training.reminder_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.training.reminder_demo.databinding.ActivityMainBinding

const val TAG = "_TAG_DEBUG"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}