import java.io.File

fun main() {
    val input = File("src/Day03_input.txt").readLines()
    println(part1(input))
    println(part2(input))
}


fun part1(input: List<String>): Int {
    return input.sumOf { rucksack ->
        rucksack.chunked(rucksack.length/2)
            .let { findSharedLetter(it.first(), it.last()).priority() }
    }
}

fun part2(input: List<String>): Int {
    return input.chunked(3).sumOf { group -> findSharedLetter(group).priority() }
}

fun findSharedLetter(first: String, last: String): Char {
    first.forEach { c: Char ->
        if (last.any { it == c }) return c
    }
    throw IllegalStateException("No duplicate char between $first and $last")
}

fun findSharedLetter(group: List<String>): Char {
    val first = group.first()
    val others = group.takeLast(2)
    return first.first { c: Char -> others.first().contains(c, ignoreCase = false) && others.last().contains(c, ignoreCase = false) }
}

val upperCase = ('A'..'Z')
val lowerCase = ('a'..'z')

fun Char.priority(): Int {
    val priority = lowerCase.indexOf(this)
    if (priority == -1) {
        return upperCase.indexOf(this) + 27
    }
    return priority + 1
}

