package pl.suwalki.zs2.thebestiilo

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Constraints
import android.support.design.widget.CollapsingToolbarLayout
import android.text.Html
import android.util.Log.d
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_details.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onTouch
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.*

class NewsDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)


        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val html = intent.getStringExtra("html")
        val date = intent.getStringExtra("date")
        val url = intent.getStringExtra("url")



        detail.text = date.subSequence(0,10)

        setTitle(title)

        toolbar_layout.title = title
        toolbar_layout.setExpandedTitleTextAppearance(R.style.expandedappbar)
        toolbar_layout.setCollapsedTitleTextAppearance(R.style.collapsedappbar)

        setSupportActionBar(newsDetailsToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }


        newsTitle.text = title
        Picasso.get().load(image).into(newsImage)

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            newsWebView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            newsWebView.text = Html.fromHtml(html)
        }*/

        newsWebView.loadData(html, "text/html", "UTF-8");

        newsWebView.settings.loadWithOverviewMode = true
        newsWebView.settings.setSupportZoom(true)
        newsWebView.settings.builtInZoomControls = true

        newsWebView.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                detailProgressBar.visibility = View.GONE
            }
        }



        var isFullscreen: Boolean = false

        /*newsImage.onClick {
            if(!isFullscreen) {
                newsImage.layoutParams = CollapsingToolbarLayout.LayoutParams(
                    CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                    CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
                )
                newsImage.scaleType = ImageView.ScaleType.FIT_CENTER
                Toast.makeText(
                    applicationContext,
                    "Aby powrócic do wpisu kliknij ponownie na OBRAZEK",
                    Toast.LENGTH_LONG
                ).show()
                isFullscreen = true
            }
            else {
                newsImage.layoutParams = CollapsingToolbarLayout.LayoutParams(
                    CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT,
                    200
                )
                newsImage.scaleType = ImageView.ScaleType.CENTER_CROP
                isFullscreen = false
            }
        }*/

        newsImage.onClick {
            if(!isFullscreen) {
                val i = Intent(applicationContext, FullscreenImage::class.java)
                i.putExtra("image", image)
                startActivity(i)
            }
            else {

            }
        }

        fabDetails.onClick {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("$url#title"))
            startActivity(i)
        }

        newsWebView.setDownloadListener { url,
                                                     _,
                                                     _,
                                                     _,
                                                     _ ->

            Toast.makeText(applicationContext, "Link zostanie otwarty w nowym oknie przeglądarki...", Toast.LENGTH_LONG).show()
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)

            startActivity(i)
        }




    }
}
