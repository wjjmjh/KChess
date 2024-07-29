package com.wjjmjh.kchess.model

import kotlin.math.abs

class Pawn(isWhite: Boolean) : Piece(isWhite) {

    fun canMove(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>, lastMove: Move?): Boolean {
        val direction = if (isWhite) 1 else -1
        val startRow = if (isWhite) 1 else 6

        // forward move
        if (startX == endX) {
            if (endY - startY == direction && board[endX][endY] == null) {
                return true
            }
            if (startY == startRow && endY - startY == 2 * direction && board[endX][endY] == null && board[startX][startY + direction] == null) {
                return true
            }
        }

        // capture move
        if (abs(startX - endX) == 1 && endY - startY == direction) {
            if (board[endX][endY] != null && board[endX][endY]?.isWhite != this.isWhite) {
                return true
            }

            // En passant
            if (isEnPassantCapture(startX, startY, endX, endY, board, lastMove)) {
                return true
            }
        }

        return false
    }

    // check if the pawn can be promoted (reaching the last row)
    fun canPromote(endY: Int): Boolean {
        return (isWhite && endY == 7) || (!isWhite && endY == 0)
    }

    // check if the move is an en passant capture
    fun isEnPassantCapture(startX: Int, startY: Int, endX: Int, endY: Int, board: Array<Array<Piece?>>, lastMove: Move?): Boolean {

        if (lastMove == null) return false

        // check if the last move was a two-square pawn advance
        if (lastMove.piece is Pawn && abs(lastMove.startY - lastMove.endY) == 2) {
            // check if the pawn being captured en passant is in the correct position
            if (lastMove.endX == endX && lastMove.endY == startY) {
                return true
            }
        }
        return false
    }
}
