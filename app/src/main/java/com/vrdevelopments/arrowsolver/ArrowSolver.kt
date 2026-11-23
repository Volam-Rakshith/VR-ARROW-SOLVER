package com.vrdevelopments.arrowsolver

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Arrow(
    val centerX: Int,
    val centerY: Int,
    val direction: Direction,
    // Approximate half-width/half-height of the arrow's visible body.
    val halfWidth: Int = 24,
    val halfHeight: Int = 24
)

object ArrowSolver {
    /**
     * Finds a valid sequence for an "arrow escape" board.
     * An arrow can leave when no remaining arrow intersects its forward ray.
     * This is a conservative rectangle-based approximation; exact game rules
     * and arrow geometry will be calibrated against real captured boards.
     */
    fun solve(input: List<Arrow>, screenWidth: Int, screenHeight: Int): List<Arrow>? {
        val remaining = input.toMutableList()
        val order = mutableListOf<Arrow>()
        while (remaining.isNotEmpty()) {
            val movable = remaining.firstOrNull { candidate ->
                remaining.none { other ->
                    other !== candidate && blocks(candidate, other, screenWidth, screenHeight)
                }
            } ?: return null
            order += movable
            remaining.remove(movable)
        }
        return order
    }

    private fun blocks(a: Arrow, b: Arrow, width: Int, height: Int): Boolean {
        return when (a.direction) {
            Direction.RIGHT ->
                b.centerX + b.halfWidth >= a.centerX &&
                b.centerX - b.halfWidth <= width &&
                kotlin.math.abs(b.centerY - a.centerY) <= a.halfHeight + b.halfHeight
            Direction.LEFT ->
                b.centerX - b.halfWidth <= a.centerX &&
                b.centerX + b.halfWidth >= 0 &&
                kotlin.math.abs(b.centerY - a.centerY) <= a.halfHeight + b.halfHeight
            Direction.DOWN ->
                b.centerY + b.halfHeight >= a.centerY &&
                b.centerY - b.halfHeight <= height &&
                kotlin.math.abs(b.centerX - a.centerX) <= a.halfWidth + b.halfWidth
            Direction.UP ->
                b.centerY - b.halfHeight <= a.centerY &&
                b.centerY + b.halfHeight >= 0 &&
                kotlin.math.abs(b.centerX - a.centerX) <= a.halfWidth + b.halfWidth
        }
    }
}
