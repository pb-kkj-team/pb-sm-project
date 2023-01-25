package pl.suwalki.zs2.thebestiilo.Service

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import pl.suwalki.zs2.thebestiilo.NewsDetails

class ShakeDetector(context: Context, url: String) : SensorEventListener {
private val context=context
    private val url = url
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val shakeThreshold = 2.5f

    fun start() {
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()) / SensorManager.GRAVITY_EARTH
        if (acceleration > shakeThreshold) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)

        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // not used
    }
}