package fr.ephyles.heroscapearmycardlist.ui.army

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        val armyList = cardList.filter { it.armyCount > 0 }

        var points = 0
        var spaces = 0

        armyList.forEach {
            points += it.points * it.armyCount
            spaces += it.figures * it.spaces * it.armyCount
        }

        view.findViewById<TextView>(R.id.army_points).text = getString(R.string.army_points, points)
        view.findViewById<TextView>(R.id.army_spaces).text = getString(R.string.army_spaces, spaces)

        //recup recyclerview
        val armyRecyclerView = view.findViewById<RecyclerView>(R.id.army_recycler_list)
        armyRecyclerView.adapter = CardAdapter(context, armyList, R.layout.item_card)

        return view
    }
}