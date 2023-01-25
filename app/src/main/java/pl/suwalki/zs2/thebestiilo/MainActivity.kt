package pl.suwalki.zs2.thebestiilo

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import com.google.android.gms.tasks.OnSuccessListener
import pl.suwalki.zs2.thebestiilo.Service.ShakeDetector


class MainActivity : AppCompatActivity() {

    private lateinit var temperatureSensor : TemperatureSensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        temperatureSensor = TemperatureSensor(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(newsDetailsToolbar)
        FirebaseApp.initializeApp(this)

        val settings = PreferenceManager.getDefaultSharedPreferences(this)

        if(settings.getBoolean("notifications_new_message", true)) {

            FirebaseMessaging.getInstance().subscribeToTopic("news-demo")
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Domyślnie włączono powiadomienia o nowych postach. Możesz je wyłączyć w ustawieniach.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("news-demo")
        }

        /*AlertDialog.Builder(this)
            .setMessage("Niestety mamy techniczne problemy ze strona internetowa przez co nie mogą pojawiać się artykuły" +
                    ", lecz zapraszamy na naszego Facebooka lub do przegladania innych opcji aplikacji.")
            .setPositiveButton("Facebook II LO") { p1, p2 ->
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/2lo.suwalki/"))
                startActivity(i)
            }
            .setNegativeButton("Kontynuuj w aplikacji") { p1, p2 ->
                // do nothing
            }
            .create()
            .show()
        */

        //FirebaseMessaging.getInstance().isAutoInitEnabled = true


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, MainFragment())
            .commit()

        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //   .setAction("Action", null).show()
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://zs2.suwalki.pl/"))
            startActivity(i)
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MainFragment())
                        .commit()
                }
                R.id.action_planlekcji -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, PlanLekcjiFragment())
                        .commit()
                }
                R.id.action_mpk -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, mpkFragment())
                        .commit()
                }
                R.id.action_facebook -> {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/2lo.suwalki/"))
                    startActivity(i)
                }
                R.id.action_promocja -> {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://promocja.zs2.suwalki.pl/"))
                    startActivity(i)
                }
                R.id.action_settings -> {
                    val intent = Intent(applicationContext, SettingsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    applicationContext.startActivity(intent)
                }



            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

    }

    override fun onResume() {
        super.onResume()
        temperatureSensor.start()
    }

    override fun onPause() {
        super.onPause()
        temperatureSensor.stop()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

}
