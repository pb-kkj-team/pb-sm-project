package pl.suwalki.zs2.thebestiilo

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import pl.suwalki.zs2.thebestiilo.model.Items
import pl.suwalki.zs2.thebestiilo.model.News

class NewsAdapter(private val news: ArrayList<Items>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        val holder = ViewHolder(view)

        view.setOnClickListener{
            val intent = Intent(parent.context, NewsDetails::class.java)
            intent.putExtra("title", news[holder.adapterPosition].title)
            intent.putExtra("image", news[holder.adapterPosition].image)
            intent.putExtra("html", news[holder.adapterPosition].content_html)
            intent.putExtra("date", news[holder.adapterPosition].date)
            intent.putExtra("url", news[holder.adapterPosition].url)
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        Picasso.get().load(news[position].image).into(holder.image)
        holder.title.text = news[position].title
        holder.text.text = news[position].content_text
        holder.detail.text = news[position].date.subSequence(0,10)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val text: TextView = itemView.findViewById(R.id.text)
        val detail: TextView = itemView.findViewById(R.id.detailRow)
    }
}