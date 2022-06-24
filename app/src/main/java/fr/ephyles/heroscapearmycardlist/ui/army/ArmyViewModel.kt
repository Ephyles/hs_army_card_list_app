package fr.ephyles.heroscapearmycardlist.ui.army

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArmyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is army Fragment"
    }
    val text: LiveData<String> = _text
}