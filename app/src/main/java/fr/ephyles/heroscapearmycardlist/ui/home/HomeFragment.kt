package fr.ephyles.heroscapearmycardlist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase.Companion.cardList
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //recup recyclerview
        val listRecyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_list)
        listRecyclerView.adapter = CardAdapter(context, cardList, R.layout.item_card) {}

        view.findViewById<Spinner>(R.id.sort_spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> listRecyclerView.adapter = CardAdapter(context, cardList, R.layout.item_card) {}
                    1 -> listRecyclerView.adapter = CardAdapter(context, cardList.asReversed(), R.layout.item_card) {}
                    2 -> listRecyclerView.adapter = CardAdapter(context, cardList.sortedBy { it.points }, R.layout.item_card) {}
                    3 -> listRecyclerView.adapter = CardAdapter(context, cardList.sortedByDescending { it.points }, R.layout.item_card) {}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        return view
    }
}