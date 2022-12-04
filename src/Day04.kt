fun main() {
    fun fullOverlap(first: IntRange, second: IntRange): Boolean {
        return (first.first >= second.first && first.last <= second.last) ||
                (first.first <= second.first && first.last >= second.last)
    }

    fun overlap(first: IntRange, second: IntRange): Boolean {
        return first.contains(second.first) || first.contains(second.last) || second.contains(first.first) || second.contains(first.last)
    }

    fun parseInput(input: List<String>): List<List<IntRange>> {
        return input.map {
            it.split(",")
                .map { elfAssignments ->
                    val range = elfAssignments.split("-")
                    IntRange(range[0].toInt(), range[1].toInt())
                }
        }
    }

    fun part1(assigmenentsPairs: List<List<IntRange>>): Int {
        return assigmenentsPairs.count { elfGroup -> fullOverlap(elfGroup.first(), elfGroup.last()) }
    }

    fun part2(assigmenentsPairs: List<List<IntRange>>): Int {
        return assigmenentsPairs.count { elfGroup -> overlap(elfGroup.first(), elfGroup.last()) }
    }

    val input = readInput("Day04_input")
    val assigmenentsPairs = parseInput(input)
    println(part1(assigmenentsPairs))
    println(part2(assigmenentsPairs))

}