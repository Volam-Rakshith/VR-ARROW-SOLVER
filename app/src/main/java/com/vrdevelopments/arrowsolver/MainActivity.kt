package com.vrdevelopments.arrowsolver

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/**
 * First milestone: an offline arrow-puzzle solver core.
 * This screen currently accepts a test board. Screen capture and accessibility
 * tapping are intentionally separate follow-up integrations requiring user consent.
 */
class MainActivity : Activity() {
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 36, 28, 36)
            setBackgroundColor(Color.rgb(242, 250, 246))
        }
        val title = TextView(this).apply {
            text = "VR ARROW SOLVER"
            textSize = 25f
            setTextColor(Color.rgb(12, 61, 45))
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = "Offline puzzle engine • Free • No account"
            textSize = 14f
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 24)
        }
        val solve = Button(this).apply {
            text = "TEST SOLVER"
            setOnClickListener {
                // Test board based on the five-arrow arrangement in the supplied image.
                val arrows = listOf(
                    Arrow(220, 110, Direction.RIGHT),
                    Arrow(120, 330, Direction.UP),
                    Arrow(260, 410, Direction.DOWN),
                    Arrow(390, 330, Direction.UP),
                    Arrow(270, 560, Direction.RIGHT)
                )
                val plan = ArrowSolver.solve(arrows, 700, 900)
                result.text = if (plan == null) {
                    "No complete solution found. The board may need a better detection pass."
                } else {
                    "Suggested removal order:\n" + plan.mapIndexed { i, a ->
                        "${i + 1}. ${a.direction} at (${a.centerX}, ${a.centerY})"
                    }.joinToString("\n") +
                    "\n\nThis is a test board, not a live screen scan."
                }
            }
        }
        result = TextView(this).apply {
            text = "Tap TEST SOLVER to run the local puzzle engine."
            textSize = 16f
            setTextColor(Color.rgb(20, 55, 43))
            setPadding(0, 24, 0, 0)
        }
        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(solve)
        layout.addView(result)
        setContentView(layout)
    }
}
