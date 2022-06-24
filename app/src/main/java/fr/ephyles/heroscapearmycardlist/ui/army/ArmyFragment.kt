package fr.ephyles.heroscapearmycardlist.ui.army

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase.Companion.cardList
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter

class ArmyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater?.inflate(R.layout.fragment_army, container, false)

        //recup recyclerview
        val armyRecyclerView = view.findViewById<RecyclerView>(R.id.army_recycler_list)
        armyRecyclerView.adapter = CardAdapter(context, cardList.filter { it.armyCount > 0 }, R.layout.item_card)

        return view
    }
}