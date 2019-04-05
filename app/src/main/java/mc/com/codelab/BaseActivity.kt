package mc.com.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class BaseActivity(layout:Int, private val TAG: String = "tests") : AppCompatActivity() {

    private var _layout : Int? = null
    init {
        this._layout=layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(_layout!=null)
            setContentView(_layout!!)

        supportActionBar!!.hide()
    }

    fun log(mess : String)= Log.i(TAG, mess)
}
