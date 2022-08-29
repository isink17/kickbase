package hr.isinkovic.kickbase

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showError(context: Activity, message: String) {
        vibrateFailure(context)

        val content = context.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(content, message, Snackbar.LENGTH_LONG)
        val layout = snackbar.view as Snackbar.SnackbarLayout
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val textView = layout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.visibility = View.INVISIBLE
        val snackView: View =
            LayoutInflater.from(context).inflate(R.layout.view_snackbar_error, null)
        snackView.findViewById<TextView>(R.id.customSnackbarText).text = message
        val paddingNormal: Int = context.resources.getDimension(R.dimen.marginNormal).toInt()
        layout.setPadding(paddingNormal, paddingNormal, paddingNormal, paddingNormal)
        layout.addView(snackView, 0)

        snackbar.show()
    }

    private fun vibrateFailure(context: Context) {
        val timings = longArrayOf(0, 100, 50, 100)
        val amplitudes = intArrayOf(0, 100, 0, 200)
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
    }

    fun checkForInternet(context: Activity): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}