package pl.suwalki.zs2.thebestiilo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_feedback.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : Fragment() {

    private lateinit var feedbackSendButton: Button
    private lateinit var feedbackText: EditText
    private lateinit var feedbackDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)


        feedbackDbRef = FirebaseDatabase.getInstance().getReference("feedbacks")

        feedbackSendButton = root.feedbackSendButton
        feedbackText = root.feedbackTextInput

        feedbackSendButton.setOnClickListener {
            val feedbackId = feedbackDbRef.push().key!!
            feedbackDbRef.child(feedbackId).setValue(feedbackText.text.toString()).addOnCompleteListener {
                Toast.makeText(context, "Dziekujemy za Twoją opinię!", Toast.LENGTH_LONG).show()
                feedbackText.setText("")
            }.addOnFailureListener { err ->
                Toast.makeText(context, "Blad: ${err.message}", Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

}