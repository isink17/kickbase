package hr.isinkovic.kickbase.ui

import android.content.Context
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import hr.isinkovic.kickbase.R
import hr.isinkovic.kickbase.api.Match
import hr.isinkovic.kickbase.databinding.MatchItemViewBinding
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.*

class MatchesViewHolder(private val binding: MatchItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var date: String? = null

    fun bind(match: Match, showDayName: Boolean) {
        binding.apply {
            loadImage(imageT1, match.firstTeam.id)
            binding.imageT1.contentDescription = match.firstTeam.name

            nameT1.text = match.firstTeam.shortName
            idT1.text = match.firstTeam.sp.toString()
            nameT2.text = match.secondTeam.shortName
            idT2.text = match.secondTeam.sp.toString()
            date = match.matchDate
            showDay(showDayName)

            loadImage(imageT2, match.secondTeam.id)
            binding.imageT2.contentDescription = match.secondTeam.name
        }

        val context: Context = binding.root.context
        when (match.status) {
            0 -> showDate(context)
            1 -> showResult(match, context.getString(R.string.lbl_match_details_started))
            2 -> showResult(match, context.getString(R.string.lbl_match_details_finished))
            4 -> showResult(match, context.getString(R.string.lbl_match_details_first))
            8 -> showResult(match, context.getString(R.string.lbl_match_details_second))
            16 -> showPostponed(context)
        }
    }

    private fun showDay(isVisible: Boolean) {
        binding.day.isVisible = isVisible
        if (isVisible) {
            binding.day.text = getZone().dayOfWeek.getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.getDefault()
            )
        }
    }

    private fun loadImage(imageView: ImageView, clubId: Int) {
        val imageLoader =
            ImageLoader.Builder(imageView.context).components { add(SvgDecoder.Factory()) }
                .build()

        val imageRequest =
            ImageRequest.Builder(imageView.context).crossfade(true).crossfade(300)
                .data("https://apidev.kickbase.com/files/teams/${clubId}/9").target(
                    imageView
                ).build()
        imageLoader.enqueue(imageRequest)
    }

    private fun getZone(): ZonedDateTime {
        return Instant.parse(date!!).atZone(ZoneId.systemDefault())
    }

    private fun showPostponed(context: Context) {
        binding.mainDetailsText.text = context.getString(R.string.lbl_match_details_none)
        binding.timeDetails.text = context.getString(R.string.lbl_match_details_postponed)
    }

    fun clearAnimation() {
        itemView.clearAnimation()
    }

    private fun showResult(match: Match, details: String) {
        binding.mainDetailsText.text = String.format(
            binding.root.context.resources.getString(
                R.string.matchDetailsPlaceholder,
                match.firstTeam.goalsCount,
                match.firstTeam.goalsCount
            )
        )

        binding.timeDetails.text = details

    }

    private fun showDate(context: Context) {
        val zone = getZone()
        binding.mainDetailsText.text = String.format(
            context.resources.getString(
                R.string.matchDetailsPlaceholder,
                zone.hour,
                zone.minute
            )
        )

        binding.timeDetails.text = String.format(
            context.getString(R.string.matchDatePlaceholder),
            zone.dayOfMonth,
            zone.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
        )
    }
}
