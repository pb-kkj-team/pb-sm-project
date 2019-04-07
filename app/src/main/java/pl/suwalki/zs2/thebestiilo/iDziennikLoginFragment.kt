package pl.suwalki.zs2.thebestiilo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_idziennik_login.*
import kotlinx.android.synthetic.main.fragment_idziennik_login.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class iDziennikLoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_idziennik_login, container, false)

        root.loginButton1.onClick {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, iDziennikFragment())
                ?.commit()
        }

        return root
    }
}