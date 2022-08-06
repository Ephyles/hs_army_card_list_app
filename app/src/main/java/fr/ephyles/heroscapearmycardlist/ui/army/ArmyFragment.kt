package fr.ephyles.heroscapearmycardlist.ui.army

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase.Companion.cardList
import fr.ephyles.heroscapearmycardlist.CardModel
import fr.ephyles.heroscapearmycardlist.Global
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter


class ArmyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_army, container, false)

        val armyList = cardList.filter { it.armyCount > 0 }

        updateArmyInfo(armyList, view)

        //recup recyclerview
        val armyRecyclerView = view.findViewById<RecyclerView>(R.id.army_recycler_list)

        when(Global.sortId) {
            0 -> armyRecyclerView.adapter = CardAdapter(context, armyList, R.layout.item_card) {
                updateArmyInfo(armyList, view)
            }
            1 -> armyRecyclerView.adapter = CardAdapter(context, armyList.asReversed(), R.layout.item_card) {
                updateArmyInfo(armyList, view)
            }
            2 -> armyRecyclerView.adapter = CardAdapter(context, armyList.sortedBy { it.points }, R.layout.item_card) {
                updateArmyInfo(armyList, view)
            }
            3 -> armyRecyclerView.adapter = CardAdapter(context, armyList.sortedByDescending { it.points }, R.layout.item_card) {
                updateArmyInfo(armyList, view)
            }
        }

        view.findViewById<Button>(R.id.clear_army_button).setOnClickListener {
            val adb: AlertDialog.Builder = AlertDialog.Builder(context)
            adb.setTitle("Clear Army?")
            adb.setMessage("Are you sure that you want to clear your current army?")
            adb.setPositiveButton("OK") { _, _ ->
                armyList.forEach {
                    it.armyCount = 0
                    updateArmyInfo(armyList, view)
                    armyRecyclerView.adapter =
                        CardAdapter(context, arrayListOf(), R.layout.item_card) {
                            updateArmyInfo(armyList, view)
                        }
                }
            }
            adb.setNegativeButton("Cancel", null)
            adb.show()
        }

        return view
    }

    private fun updateArmyInfo(armyList: List<CardModel>, view: View) {
        var points = 0
        var spaces = 0

        armyList.forEach {
            points += it.points * it.armyCount
            spaces += it.figures * it.spaces * it.armyCount
        }

        view.findViewById<TextView>(R.id.army_points).text = getString(R.string.army_points, points)
        view.findViewById<TextView>(R.id.army_spaces).text = getString(R.string.army_spaces, spaces)
    }
}