package fr.ephyles.heroscapearmycardlist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase
import fr.ephyles.heroscapearmycardlist.CardDatabase.Companion.cardList
import fr.ephyles.heroscapearmycardlist.CardModel
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //recup recyclerview
        val listRecyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_list)
        listRecyclerView.adapter = CardAdapter(context, cardList.sortedBy { it.points }, R.layout.item_card) {}

        return view
    }
}