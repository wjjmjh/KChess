package com.wjjmjh.kchess.controller

import com.wjjmjh.kchess.model.*

class GameController {
    private val board = Board()
    private var lastMove: Move? = null

    fun movePiece(startX: Int, startY: Int, endX: Int, endY: Int): Boolean {
        val piece = board.board[startX][startY] ?: return false

        val canMove = when (piece) {
            is Bishop -> piece.canMove(startX, startY, endX, endY, board.board)
            is King -> piece.canMove(startX, startY, endX, endY, board.board)
            is Knight -> piece.canMove(startX, startY, endX, endY, board.board)
            is Pawn -> piece.canMove(startX, startY, endX, endY, board.board, lastMove)
            is Queen -> piece.canMove(startX, startY, endX, endY, board.board)
            is Rook -> piece.canMove(startX, startY, endX, endY, board.board)
            else -> false
        }

        if (canMove) {
            board.board[endX][endY] = piece
            board.board[startX][startY] = null

            // check for en passant capture
            if (piece is Pawn && piece.isEnPassantCapture(startX, startY, endX, endY, board.board, lastMove)) {
                // capture the opponent's pawn
                board.board[endX][startY] = null
            }

            // check for promotion
            if (piece is Pawn && piece.canPromote(endY)) {
                // TODO
            }

            // update last move
            lastMove = Move(startX, startY, endX, endY, piece)
        }

        return canMove
    }
}
