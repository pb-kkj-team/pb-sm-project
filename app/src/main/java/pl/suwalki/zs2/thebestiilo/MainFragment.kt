package pl.suwalki.zs2.thebestiilo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.suwalki.zs2.thebestiilo.model.Items
import pl.suwalki.zs2.thebestiilo.model.News
import java.net.URL

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        activity?.title = "II LO"

        doAsync {
            val json = URL("https://zs2.suwalki.pl/feed/json").readText()
            d("testing", json.length.toString())
            uiThread {

                val news = Gson().fromJson(json, News::class.java)

                // d("testing2", news.items[0].content) <3333333
                val newsList = arrayListOf<Items>()
                news.items.forEach {
                    d("testing33", it.title)
                    newsList.add(Items(it.id, it.url, it.title, it.content_text, it.content_html, it.image, it.date))
                }

                root.recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = NewsAdapter(newsList)
                    root.progressBar.visibility = View.GONE
                }



            }
        }



        return root
    }
}