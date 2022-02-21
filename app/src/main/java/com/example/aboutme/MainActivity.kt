package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val aboutName: MyName = MyName("Kraven Daemon")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // dataclass binded here
        binding.myName = aboutName
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname(currentButtonView: View){
        // replacing findViewById with dataBindings feature
        binding.apply {
            // here with <dataclass> instead of <bindingview> class
            ("Alias : " + nicknameEdit.text).also { aboutName.nickname = it }
            // not sure about that one
            // it reset the bindings?
            invalidateAll()
            // hide form and button
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            // show new text
            nicknameText .visibility = View.VISIBLE
        }

        // Hide the keyboard some extra imports required
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentButtonView.windowToken, 0)

    }
}
data class MyName(
    var name: String = "",
    var nickname: String = ""
)