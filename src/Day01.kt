import java.io.File

fun main() {
    fun part1(input: String): Int {
        return input.split("\n\n").maxOfOrNull { elf -> elf.lines().sumOf { it.toInt() } } ?: 0
    }

    fun part2(input: String): Int {
        return input
            .split("\n\n")
            .map { elf -> elf.lines().sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)
            .sum()
    }

    val testInput = File("src/Day01_test.txt").readText()
    check(part1(testInput) == 24000)

    val input = File("src/Day01_input.txt").readText()
    println(part1(input))

    println(part2(input))
}
