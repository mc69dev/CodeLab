package mc.com.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import mc.com.codelab.tools.Util


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        btn_start_1.setOnClickListener {
           Util.start(applicationContext, NotifyActivity::class.java)
        }
        anim()
    }

    private fun anim() {
        //logo.alpha=0f
        var anim = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in)
        anim.fillAfter=true
        logo.startAnimation(anim)

        /*logo.alpha = 0.2f
        logo.animate().apply {
            interpolator = LinearInterpolator()
            duration = 3000
            alpha(1f)
            startDelay = 1500
            start()
        }*/
    }

}
