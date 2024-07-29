package com.wjjmjh.kchess.model

import kotlin.math.abs

class King(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>): Boolean {
        val dx = abs(endX - startX)
        val dy = abs(endY - startY)

        // King can move one square in any direction
        if (dx > 1 || dy > 1) {
            return false
        }

        // check if the destination is occupied by a piece of the same color
        val destinationPiece = board[endX][endY]
        return !(destinationPiece != null && destinationPiece.isWhite == this.isWhite)
    }
}
