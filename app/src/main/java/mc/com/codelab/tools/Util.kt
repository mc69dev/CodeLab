package mc.com.codelab.tools

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewAnimationUtils

class Util {

    /**
     * static
     */
    companion object{

        /**
         * Activities
         */
        fun start(context: Context, activity : Class<*>) {
            val intent = Intent(context,activity)
            context.startActivity(intent)
        }

        /**
         * Animations
         */
        fun circleAnime(view: View) {
            val duration = view.context.resources.getInteger(android.R.integer.config_longAnimTime).toLong()
            val cx = view.width / 2
            val cy = view.height / 2
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
            view.visibility = View.VISIBLE
            anim.setDuration(duration).start()
        }

    }
}