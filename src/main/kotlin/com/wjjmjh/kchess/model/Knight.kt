package com.wjjmjh.kchess.model

import kotlin.math.abs

class Knight(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>): Boolean {
        val dx = abs(endX - startX)
        val dy = abs(endY - startY)

        // Knight moves in an "L" shape: 2 squares in one direction and 1 square perpendicular, or vice versa
        if (!((dx == 2 && dy == 1) || (dx == 1 && dy == 2))) {
            return false
        }

        // check if the destination is occupied by a piece of the same color
        val destinationPiece = board[endX][endY]
        return !(destinationPiece != null && destinationPiece.isWhite == this.isWhite)
    }
}
