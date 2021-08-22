package master.training.reminder_demo.movementlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import master.training.reminder_demo.R

class MovementAdapter(
    var event: MutableLiveData<MovementListEvent> = MutableLiveData(),
    private val movements: List<MovementListItem>
) : RecyclerView.Adapter<MovementAdapter.MovementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovementViewHolder(
            inflater.inflate(R.layout.item_movement, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        movements[position].let { item ->
            holder.movementTitle.text = item.name

/*
            bindTargets(item, holder)
            showDifficultyIcons(item.difficulty, holder)
*/

            holder.itemView.setOnClickListener {
                event.value = MovementListEvent.OnMovementClick(
                    movements[position].name
                )
            }

        }
    }

    override fun getItemCount(): Int = movements.size

    /*
    * in
    * */

    internal fun setObserver(observer: Observer<MovementListEvent>) = event.observeForever(observer)
    private fun showDifficultyIcons(difficulty: Int, holder: MovementViewHolder) {
    }

    private fun bindTargets(item: MovementListItem, holder: MovementViewHolder) {
    }

    /*
    *
    * viewHolder
    * */
    class MovementViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        var movementTitle: TextView = root.findViewById(R.id.lblMovementItemName)
    }

}