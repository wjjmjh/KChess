package com.wjjmjh.kchess.model

import kotlin.math.abs

class Bishop(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>): Boolean {
        val dx = abs(endX - startX)
        val dy = abs(endY - startY)

        // check if the move is diagonal
        if (dx != dy) {
            return false
        }

        // check if the path is clear
        val xStep = if (endX > startX) 1 else -1
        val yStep = if (endY > startY) 1 else -1

        var x = startX + xStep
        var y = startY + yStep
        while (x != endX && y != endY) {
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
