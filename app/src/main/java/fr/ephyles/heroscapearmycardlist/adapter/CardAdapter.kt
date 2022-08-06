package fr.ephyles.heroscapearmycardlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardModel
import fr.ephyles.heroscapearmycardlist.CardPopup
import fr.ephyles.heroscapearmycardlist.R

class CardAdapter(
    val context: Context?,
    private val cardList: List<CardModel>,
    private val layoutId: Int,
    val callback: () -> Unit
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    // Range les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val generalIcon: ImageView = view.findViewById(R.id.general_icon_item)
        val cardName: TextView = view.findViewById(R.id.name_item)
        val generalName: TextView = view.findViewById(R.id.general_item)
        val cardPoints: TextView = view.findViewById(R.id.points_item)
        val armyCount: TextView = view.findViewById(R.id.army_count_item)
        val minusButton: ImageView = view.findViewById(R.id.minus_button_item)
        val plusButton: ImageView = view.findViewById(R.id.plus_button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recup infos carte
        val currentCard = cardList[position]

        // maj nom general et points de la carte
        holder.cardName.text = currentCard.cardname

        if(currentCard.general == "Destructible Object") {
            holder.generalName.text = "D. Object"
        } else {
            holder.generalName.text = currentCard.general
        }

        holder.cardPoints.text = currentCard.points.toString()
        holder.armyCount.text = currentCard.armyCount.toString()

        when(currentCard.general) {
            "Einar" -> holder.generalIcon.setImageResource(R.drawable.einar_colored)
            "Aquilla" -> holder.generalIcon.setImageResource(R.drawable.aquilla_colored)
            "Utgar" -> holder.generalIcon.setImageResource(R.drawable.utgar_colored)
            "Ullar" -> holder.generalIcon.setImageResource(R.drawable.ullar_colored)
            "Vydar" -> holder.generalIcon.setImageResource(R.drawable.vydar_colored)
            "Valkrill" -> holder.generalIcon.setImageResource(R.drawable.valkrill_colored)
            "Jandar" -> holder.generalIcon.setImageResource(R.drawable.jandar_colored)
            else -> holder.generalIcon.setImageResource(R.drawable.defaultgen_colored)
        }

        holder.minusButton.setOnClickListener {
            if (currentCard.armyCount > 0) {
                currentCard.armyCount--
                holder.armyCount.text = currentCard.armyCount.toString()
                callback()
            }
        }

        holder.plusButton.setOnClickListener {
            if (currentCard.armyCount == 0 || !currentCard.rarityAndNumber.contains("Unique", true)) {
                currentCard.armyCount++
                holder.armyCount.text = currentCard.armyCount.toString()
                callback()
            }
        }

        holder.itemView.setOnClickListener {
            CardPopup(this, currentCard) {
                holder.armyCount.text = currentCard.armyCount.toString()
                callback()
            }.show()
        }
    }

    override fun getItemCount(): Int = cardList.size

}