package hr.isinkovic.kickbase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import hr.isinkovic.kickbase.api.Match
import hr.isinkovic.kickbase.databinding.MatchItemViewBinding
import java.time.Instant
import java.time.ZoneId

class MatchesAdapter : RecyclerView.Adapter<MatchesViewHolder>() {
    private val matches = ArrayList<Match>()

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(
            MatchItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val isDifferentDay =
            position > 0 && getDayName(matches[position].matchDate) != getDayName(matches[position - 1].matchDate)
        val showDayName = position == 0 || isDifferentDay
        holder.bind(matches[position], showDayName)

        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(view.context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun onViewDetachedFromWindow(holder: MatchesViewHolder) {
        holder.clearAnimation()
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    private fun getDayName(date: String): String {
        return Instant.parse(date).atZone(ZoneId.systemDefault()).dayOfWeek.name
    }

    fun clear() {
        val size = matches.size
        matches.clear()
        notifyItemRangeRemoved(0, size)
        lastPosition = -1
    }

    fun addAll(list: List<Match>) {
        matches.addAll(list)
        notifyItemRangeInserted(0, matches.size)
    }
}