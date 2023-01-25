package pl.suwalki.zs2.thebestiilo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast



class TemperatureSensor(context: Context) : SensorEventListener {
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val temperatureSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    private val context = context;

    fun start() {
        if(temperatureSensor != null)
        {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stop() {
        if(temperatureSensor != null)
            sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(temperatureSensor != null) {


            if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                val temperature = event.values[0]
                if (temperature < -20) {
                    Toast.makeText(context, "Z powodu fatalnych warunków atmosferycznych. Zajecia są odwołane.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}