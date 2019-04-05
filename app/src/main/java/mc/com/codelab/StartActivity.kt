package mc.com.codelab

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*
import mc.com.codelab.tools.Util

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        logo.setOnClickListener { Util.start(applicationContext, MainActivity::class.java) }
        supportActionBar!!.hide()
        loading()
    }

    private fun loading() {
        val loading = findViewById<ProgressBar>(R.id.loading)
        var duration = resources.getInteger(android.R.integer.config_longAnimTime)

        loading.animate()
            .alpha(0f)
            .setDuration(duration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    loading.visibility = View.GONE
                    display()
                }

                private fun display() {
                    Util.circleAnime(tvtitle)
                    Util.circleAnime(logo)
                    gotoAfterDelay()
                }

                private fun gotoAfterDelay() {
                    Handler().postDelayed(
                        {
                            Util.start(applicationContext, MainActivity::class.java)
                        },
                        duration.toLong()
                    )
                }
            })
    }
}
