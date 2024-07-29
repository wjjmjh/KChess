package com.wjjmjh.kchess.model

class Rook(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>): Boolean {

        // Rook can only move in straight lines (either along rows or columns)
        if (startX != endX && startY != endY) {
            return false
        }

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
