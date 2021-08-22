package master.training.reminder_demo.movementlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import master.training.reminder_demo.other.BaseLogic
import master.training.reminder_demo.R
import master.training.reminder_demo.TAG
import master.training.reminder_demo.a_domain.model.Movement
import master.training.reminder_demo.databinding.FragmentMovementListBinding
import master.training.reminder_demo.movementlist.buildlogic.MovementListInjector
import master.training.reminder_demo.other.getTargetsByResource
import master.training.reminder_demo.other.getThumbnailByResourceName
import master.training.reminder_demo.other.showToast

class MovementListFragment : Fragment(),
    MovementListContract.View {

    // binding
    private var _binding: FragmentMovementListBinding? = null
    private val binding get() = _binding!!

    // in
    var logic: BaseLogic<MovementListEvent>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovementListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logic?.handleEvent(MovementListEvent.OnStop)
        binding.recMovementList.adapter = null
    }

    override fun onStart() {
        super.onStart()

        logic = MovementListInjector.provideLogic(
            requireActivity().application,
            this
        )


        setUpBottomNav()
        logic?.handleEvent(MovementListEvent.OnStart)


    }

    override fun setMovementListData(movementListData: List<Movement>) {
        val movementAdapter = MovementAdapter(
            movements = movementListData.toMovementItemList()
        )

        /*
        *
        * forward event to logic class
        * */

        movementAdapter.event.observe(this,
            Observer {
                logic?.handleEvent(it)
            }
        )

        binding.apply {
            recMovementList.adapter = movementAdapter
            proMovementList.visibility = View.INVISIBLE
            recMovementList.visibility = View.VISIBLE
        }
    }

    override fun startMovementView(movementId: String) {
        Log.d(TAG, "startMovementView: $movementId")
    }

    override fun startSettingsView() {
        Log.d(TAG, "startSettingsView: ")
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun startRemindersView() {
        if (findNavController().currentDestination?.id == R.id.movementListFragment) {
            findNavController().navigate(
                R.id.action_movementListFragment_to_reminderListFragment
            )
        }
    }

    private fun setUpBottomNav() {
        binding.bottomNavMovements.apply {
            setupWithNavController(findNavController())
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.reminderListFragment -> logic?.handleEvent(MovementListEvent.OnRemindersClick)
                }
                true
            }
            selectedItemId = R.id.movementListFragment
        }
    }

    fun List<Movement>.toMovementItemList(): List<MovementListItem> {
        val newList = mutableListOf<MovementListItem>()
        this.forEach { movement ->
            newList.add(
                MovementListItem(
                    movement.name,
                    getTargetsByResource(movement.targets),
                    getThumbnailByResourceName(movement.imageResourceNames[0]),
                    movement.difficulty.difficultyToInt()
                )
            )
        }
        return newList
    }

    private fun String.difficultyToInt(): Int {
        return when (this) {
            "Easy" -> 1
            "Medium" -> 2
            "Advanced" -> 3
            else -> 2
        }
    }
}
