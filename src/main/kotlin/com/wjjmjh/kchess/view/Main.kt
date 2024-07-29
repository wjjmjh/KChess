package com.wjjmjh.kchess.view

import com.wjjmjh.kchess.controller.GameController

fun main() {
    val controller = GameController()
    val gameOver = false
    var currentPlayerIsWhite = true

    while (!gameOver) {
        println("current board:")
        printBoard(controller)

        val currentPlayer = if (currentPlayerIsWhite) "white" else "black"
        println("$currentPlayer's turn")
        print("enter move (e.g., a2 a4): ")
        val input = readlnOrNull() ?: break

        val move = input.split(" ")
        if (move.size != 2) {
            println("invalid input, please enter moves in the format 'a2 a4'.")
            continue
        }

        val (start, end) = move
        val (startX, startY) = parsePosition(start)
        val (endX, endY) = parsePosition(end)

        if (startX == -1 || startY == -1 || endX == -1 || endY == -1) {
            println("invalid input, please enter moves in the format 'a2 a4'.")
            continue
        }

        val validMove = controller.movePiece(startX, startY, endX, endY)
        if (!validMove) {
            println("invalid move; try again.")
            continue
        }

        // players swap
        currentPlayerIsWhite = !currentPlayerIsWhite

        // TODO: gameOver = checkGameOver()
    }
}

fun printBoard(controller: GameController) {
    // TODO
}

fun parsePosition(position: String): Pair<Int, Int> {
    if (position.length != 2) return Pair(-1, -1)

    val file = position[0].lowercaseChar()
    val rank = position[1]

    val startX = file - 'a'
    val startY = rank - '1'

    if (startX !in 0..7 || startY !in 0..7) return Pair(-1, -1)

    return Pair(startX, startY)
}
