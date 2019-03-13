package pl.suwalki.zs2.thebestiilo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_mpk.view.*

class mpkFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_mpk, container, false)

        activity?.title = "Odjazdy MPK"

        root.mpkWebView.loadUrl("http://rozklady.suwalki.pl/Home/TimeTableReal?busStopId=84")
        root.mpkWebView.settings.javaScriptEnabled = true
        root.mpkWebView.settings.loadWithOverviewMode = true
        root.mpkWebView.settings.domStorageEnabled = true
        root.mpkWebView.settings.useWideViewPort = true

        root.mpkWebView.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                root.mpkProgressBar2.visibility = View.GONE
            }
        }

        root.mpkWebView2.loadUrl("http://rozklady.suwalki.pl/Home/TimeTableReal?busStopId=85")
        root.mpkWebView2.settings.javaScriptEnabled = true
        root.mpkWebView2.settings.loadWithOverviewMode = true
        root.mpkWebView2.settings.domStorageEnabled = true
        root.mpkWebView2.settings.useWideViewPort = true

        //TODO: Poprawic lokalizacje progressbara

        return root
    }
}