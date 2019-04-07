package pl.suwalki.zs2.thebestiilo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_idziennik.*
import kotlinx.android.synthetic.main.fragment_idziennik.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class iDziennikFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_idziennik, container, false)

        activity?.title = "iDziennik"

        root.idziennikWebView.loadUrl("https://iuczniowie.progman.pl/idziennik/login.aspx")



        root.idziennikWebView.settings.javaScriptEnabled = true
        root.idziennikWebView.settings.loadWithOverviewMode = true
        root.idziennikWebView.settings.setSupportZoom(true)
        root.idziennikWebView.settings.builtInZoomControls = true
        root.idziennikWebView.settings.domStorageEnabled = true

        root.idziennikWebView.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                root.idziennikProgressBar.visibility = View.GONE
                //UserName Password document.getElementById('UserName').value = 'XD';

                root.idziennikWebView.loadUrl("javascript:var uselessvar =document.getElementById('UserName').value = ''")
            }
        }

        return root
    }
}