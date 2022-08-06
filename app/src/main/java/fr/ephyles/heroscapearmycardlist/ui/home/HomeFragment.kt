package fr.ephyles.heroscapearmycardlist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ephyles.heroscapearmycardlist.CardDatabase.Companion.cardList
import fr.ephyles.heroscapearmycardlist.CardModel
import fr.ephyles.heroscapearmycardlist.GeneralDialogFragment
import fr.ephyles.heroscapearmycardlist.Global
import fr.ephyles.heroscapearmycardlist.Global.*
import fr.ephyles.heroscapearmycardlist.R
import fr.ephyles.heroscapearmycardlist.adapter.CardAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var filteredCardList = cardList

        val listToShow = filteredCardList.filter {
                    (generalFilter == "All" || it.general == generalFilter) &&
                    (it.cat != "Official" || official) &&
                    (it.cat != "C3V" || c3v) &&
                    (it.cat != "SoV" || sov)
        }

        //recup recyclerview
        val listRecyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_list)
        listRecyclerView.adapter = CardAdapter(context, listToShow, R.layout.item_card) {}

        view.findViewById<Spinner>(R.id.sort_spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val listToShow = filteredCardList.filter {
                    (generalFilter == "All" || it.general == generalFilter) &&
                            (it.cat != "Official" || official) &&
                            (it.cat != "C3V" || c3v) &&
                            (it.cat != "SoV" || sov)
                }

                when(position) {
                    0 -> {
                        listRecyclerView.adapter = CardAdapter(context, listToShow, R.layout.item_card) {}
                        Global.sortId = 0
                    }
                    1 -> {
                        listRecyclerView.adapter = CardAdapter(context, listToShow.asReversed(), R.layout.item_card) {}
                        Global.sortId = 1
                    }
                    2 -> {
                        listRecyclerView.adapter = CardAdapter(context, listToShow.sortedBy { it.points }, R.layout.item_card) {}
                        Global.sortId = 2
                    }
                    3 -> {
                        listRecyclerView.adapter = CardAdapter(context, listToShow.sortedByDescending { it.points }, R.layout.item_card) {}
                        Global.sortId = 3
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val searchview = view.findViewById<SearchView>(R.id.search_view)
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                searchview.clearFocus()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                filteredCardList = cardList.filter{
                    it.cardname.lowercase().contains(s.lowercase()) ||
                    it.species.lowercase().contains(s.lowercase()) ||
                    it.Class.lowercase().contains(s.lowercase()) ||
                    it.personality.lowercase().contains(s.lowercase()) ||
                    it.figSize.lowercase().contains(s.lowercase()) } as ArrayList<CardModel>

                val listToShow = filteredCardList.filter {
                    (generalFilter == "All" || it.general == generalFilter) &&
                            (it.cat != "Official" || official) &&
                            (it.cat != "C3V" || c3v) &&
                            (it.cat != "SoV" || sov)
                }

                when(Global.sortId) {
                    0 -> listRecyclerView.adapter = CardAdapter(context, listToShow, R.layout.item_card) {}
                    1 -> listRecyclerView.adapter = CardAdapter(context, listToShow.asReversed(), R.layout.item_card) {}
                    2 -> listRecyclerView.adapter = CardAdapter(context, listToShow.sortedBy { it.points }, R.layout.item_card) {}
                    3 -> listRecyclerView.adapter = CardAdapter(context, listToShow.sortedByDescending { it.points }, R.layout.item_card) {}
                }

                return false
            }
        })

        view.findViewById<Button>(R.id.general_filter_button).setOnClickListener {
            val generalPopupFragment = GeneralDialogFragment {

                val listToShow = filteredCardList.filter {
                    (generalFilter == "All" || it.general == generalFilter) &&
                            (it.cat != "Official" || official) &&
                            (it.cat != "C3V" || c3v) &&
                            (it.cat != "SoV" || sov)
                }

                when(sortId) {
                    0 -> listRecyclerView.adapter = CardAdapter(context, listToShow, R.layout.item_card) {}
                    1 -> listRecyclerView.adapter = CardAdapter(context, listToShow.asReversed(), R.layout.item_card) {}
                    2 -> listRecyclerView.adapter = CardAdapter(context, listToShow.sortedBy { it.points }, R.layout.item_card) {}
                    3 -> listRecyclerView.adapter = CardAdapter(context, listToShow.sortedByDescending { it.points }, R.layout.item_card) {}
                }
            }

            generalPopupFragment.show(childFragmentManager, "generalPopup")
        }

        return view
    }
}