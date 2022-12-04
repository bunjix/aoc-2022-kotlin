import java.io.File

fun main() {
    val input = File("src/Day02_input.txt").readLines()
    val fomattedInput = input.map { line -> line.split(" ").let { players -> players.first().first() to players.last().first() } }
    println(part1(fomattedInput))
    println(part2(fomattedInput))
}

private fun part1(input: List<Pair<Char, Char>>): Int {
    return input.sumOf { (other, me) -> me.shapeScore() + me.roundOutcome(other) }
}

private fun part2(input: List<Pair<Char, Char>>): Int {
    /*
    return input.sumOf { (other, outcome) ->
        val me = outcome.shapeOutcome(other)
        me.shapeScore() + me.roundOutcome(other)
    }
     */
    return part1(input.map { (other, outcome) -> other to outcome.shapeOutcome(other) })
}

fun Char.shapeScore(): Int {
    return when (this) {
        'X', 'A' -> 1
        'Y', 'B' -> 2
        'Z', 'C' -> 3
        else -> throw IllegalStateException("Wrong shape $this")
    }
}

fun Char.shapeOutcome(other: Char): Char {
    return when {
        this == 'X' && other == 'A' -> 'C'
        this == 'X' && other == 'B' -> 'A'
        this == 'X' && other == 'C' -> 'B'

        this == 'Y' -> other

        this == 'Z' && other == 'A' -> 'B'
        this == 'Z' && other == 'B' -> 'C'
        this == 'Z' && other == 'C' -> 'A'
        else -> throw IllegalStateException("Wrong hand ($this, $other)")
    }
}

fun Char.mapShape(): Char {
    return when (this) {
        'X' -> 'A'
        'Y' -> 'B'
        'Z' -> 'C'
        else -> this
    }
}
fun Char.roundOutcome(other: Char): Int {
    val myShape = this.mapShape()
    return when {
        myShape == 'A' && other == 'A' -> 3
        myShape == 'A' && other == 'B' -> 0
        myShape == 'A' && other == 'C' -> 6

        myShape == 'B' && other == 'A' -> 6
        myShape == 'B' && other == 'B' -> 3
        myShape == 'B' && other == 'C' -> 0

        myShape == 'C' && other == 'A' -> 0
        myShape == 'C' && other == 'B' -> 6
        myShape == 'C' && other == 'C' -> 3
        else -> throw IllegalStateException("Wrong hand ($this, $other)")
    }
}