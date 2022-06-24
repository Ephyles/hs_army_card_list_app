package fr.ephyles.heroscapearmycardlist.ui.army

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase
import fr.ephyles.heroscapearmycardlist.CardModel
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter

class ArmyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater?.inflate(R.layout.fragment_army, container, false)

        val db = CardDatabase(context)
        val cards = db.armyCards

        val cardList = arrayListOf<CardModel>()
        with(cards) {
            while (moveToNext()) {
                var newCard = CardModel(
                    getString(0),
                    getString(15),
                    getInt(7),
                    getString(1),
                    getInt(2),
                    getInt(3),
                    getInt(4),
                    getInt(5),
                    getInt(6),
                    getString(8) + " " + getInt(9).toString(),
                    getString(10),
                    getString(11),
                    getString(12),
                    getString(13) + " " + getString(14),
                    getInt(16),
                    getInt(17),
                    getString(18) + "\n\n" + getString(19) + "\n\n" +
                            getString(20) + "\n\n" + getString(21) + "\n\n" +
                            getString(22) + "\n\n" + getString(23) + "\n\n" +
                            getString(24) + "\n\n" + getString(25) + "\n\n",
                    getString(26),
                    0
                )
                cardList.add(newCard)
            }
        }
        cards.close()

        //recup recyclerview
        val armyRecyclerView = view.findViewById<RecyclerView>(R.id.army_recycler_list)
        armyRecyclerView.adapter = CardAdapter(context, cardList.filter { it.armyCount > 0 }, R.layout.item_card)

        return view
    }
}