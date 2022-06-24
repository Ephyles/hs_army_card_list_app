package fr.ephyles.heroscapearmycardlist

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter


class CardPopup(private val adapter: CardAdapter, private val currentCard: CardModel, private val callback: () -> Unit) : Dialog(adapter.context!!) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_card_details)
        setupComponents()
        setupCloseButton()
        setupBookButton()
        setupPlusMinus()
    }

    private fun setupComponents() {
        val generalIcon = findViewById<ImageView>(R.id.general_icon_popup)
        when(currentCard.general) {
            "Einar" -> generalIcon.setImageResource(R.drawable.einar)
            "Aquilla" -> generalIcon.setImageResource(R.drawable.aquilla)
            "Utgar" -> generalIcon.setImageResource(R.drawable.utgar)
            "Ullar" -> generalIcon.setImageResource(R.drawable.ullar)
            "Vydar" -> generalIcon.setImageResource(R.drawable.vydar)
            "Valkrill" -> generalIcon.setImageResource(R.drawable.valkrill)
            "Jandar" -> generalIcon.setImageResource(R.drawable.jandar)
            else -> generalIcon.setImageResource(R.drawable.ic_close)
        }

        findViewById<TextView>(R.id.card_name_popup).text = currentCard.cardname
        findViewById<TextView>(R.id.card_general_cat_popup).text = adapter.context?.getString(R.string.general_category_popup, currentCard.general, currentCard.cat)

        findViewById<TextView>(R.id.species_popup).text = currentCard.species
        findViewById<TextView>(R.id.rarity_number_popup).text = currentCard.rarityAndNumber
        findViewById<TextView>(R.id.class_popup).text = currentCard.Class
        findViewById<TextView>(R.id.personality_popup).text = currentCard.personality
        findViewById<TextView>(R.id.size_popup).text = currentCard.figSize

        findViewById<TextView>(R.id.figures_popup).text = adapter.context?.getString(R.string.figures_popup, currentCard.figures)
        findViewById<TextView>(R.id.spaces_popup).text = adapter.context?.getString(R.string.spaces_popup, currentCard.spaces)
        findViewById<TextView>(R.id.total_spaces_popup).text = adapter.context?.getString(R.string.total_spaces_popup, currentCard.figures * currentCard.spaces)

        findViewById<TextView>(R.id.lives_popup).text = adapter.context?.getString(R.string.lives_popup, currentCard.lifeStat)
        findViewById<TextView>(R.id.move_popup).text = adapter.context?.getString(R.string.move_popup, currentCard.moveStat)
        findViewById<TextView>(R.id.attack_popup).text = adapter.context?.getString(R.string.attack_popup, currentCard.attackStat)
        findViewById<TextView>(R.id.range_popup).text = adapter.context?.getString(R.string.range_popup, currentCard.rangeStat)
        findViewById<TextView>(R.id.defense_popup).text = adapter.context?.getString(R.string.defense_popup, currentCard.defenseStat)
        findViewById<TextView>(R.id.points_popup).text = adapter.context?.getString(R.string.points_popup, currentCard.points)

        // Sometimes apostrophes are glitched and become "ÔÇÖ", same with hyphens becoming "ÔÇæ"
        findViewById<TextView>(R.id.powers_popup).text = currentCard.powers
            .replace("ÔÇÖ", "'") //Fix apostrophes
            .replace("ÔÇæ", "-") //Fix hyphens

        findViewById<TextView>(R.id.army_count_popup).text = currentCard.armyCount.toString()
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            dismiss()
            callback()
        }
    }

    private fun setupBookButton() {
        findViewById<ImageButton>(R.id.book_button_popup).setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(currentCard.bookUrl)
            context.startActivity(intent)
        }
    }

    private fun setupPlusMinus() {
        findViewById<ImageView>(R.id.minus_button_popup).setOnClickListener {
            if (currentCard.armyCount > 0) {
                currentCard.armyCount--
                findViewById<TextView>(R.id.army_count_popup).text = currentCard.armyCount.toString()
            }
        }

        findViewById<ImageView>(R.id.plus_button_popup).setOnClickListener {
            if (currentCard.armyCount == 0 || !currentCard.rarityAndNumber.contains("Unique", true)) {
                currentCard.armyCount++
                findViewById<TextView>(R.id.army_count_popup).text = currentCard.armyCount.toString()
            }
        }
    }
}