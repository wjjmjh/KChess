package com.wjjmjh.kchess.model

import kotlin.math.abs

class Queen(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>): Boolean {
        val dx = abs(endX - startX)
        val dy = abs(endY - startY)

        // check if the move is along a row, column, or diagonal
        if (startX != endX && startY != endY && dx != dy) {
            return false
        }

        // determine the direction of movement
        val xStep = when {
            endX > startX -> 1
            endX < startX -> -1
            else -> 0
        }

        val yStep = when {
            endY > startY -> 1
            endY < startY -> -1
            else -> 0
        }

        // check if the path is clear
        var x = startX + xStep
        var y = startY + yStep
        while (x != endX || y != endY) {
            if (board[x][y] != null) {
                return false
            }
            x += xStep
            y += yStep
        }

        // check if the destination is occupied by a piece of the same color
        val destinationPiece = board[endX][endY]
        return !(destinationPiece != null && destinationPiece.isWhite == this.isWhite)
    }
}
