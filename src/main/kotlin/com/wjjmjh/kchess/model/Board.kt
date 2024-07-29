package com.wjjmjh.kchess.model

class Board {
    val board = Array(8) { arrayOfNulls<Piece>(8) }

    init {
        setupPieces()
    }

    private fun setupPieces() {}
}
