package pl.suwalki.zs2.thebestiilo

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_planlekcji.*
import kotlinx.android.synthetic.main.fragment_planlekcji.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.suwalki.zs2.thebestiilo.model.Items
import pl.suwalki.zs2.thebestiilo.model.News
import java.net.URL

class PlanLekcjiFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_planlekcji, container, false)
        activity?.title = "Plan lekcji"
        doAsync {
            val json = URL("https://zs2.suwalki.pl/plan-lekcji/feed/json").readText()
            uiThread {

                val news = Gson().fromJson(json, News::class.java)

                news.items.forEach {
                    root.planlekcjiWebView.loadData(it.content_html, "text/html", "UTF-8")
                }

                root.planlekcjiWebView.settings.loadWithOverviewMode = true
                root.planlekcjiWebView.settings.setSupportZoom(true)
                root.planlekcjiWebView.settings.builtInZoomControls = true

                root.planlekcjiWebView.webViewClient = object: WebViewClient(){
                    override fun onPageFinished(view: WebView?, url: String?) {
                        root.planlekcjiProgressBar.visibility = View.GONE
                    }
                }

                root.planlekcjiWebView.setDownloadListener { url,
                                                             _,
                                                             _,
                                                             _,
                                                             _ ->

                    Toast.makeText(context, "Pobieranie otworzy się w oknie przeglądarki...", Toast.LENGTH_LONG).show()
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)

                    startActivity(i)
                }

            }
        }


        return root
    }
}