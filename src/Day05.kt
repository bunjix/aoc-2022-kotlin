fun main() {

    fun part1(input: List<String>, newCrane: Boolean): String {
        val (stacksInput, craneStepsInput) = splitInput(input)

        val stackCount = "(\\d*)\\s\$".toRegex().find(stacksInput.last())?.groupValues.orEmpty().last().toInt()
        val stacks = mutableListOf<MutableList<Char>>()
        repeat(stackCount) {
            stacks.add(mutableListOf())
        }
        stacksInput.dropLast(1)
            .map { line ->
                line.chunked(4) { it.toString().replace("[", "").replace("]", "").trim() }
                    .forEachIndexed { index, s ->
                        if (s.isNotEmpty()) {
                            val stack = stacks[index]
                            stack.add(0, s.first())
                        }
                    }
            }
        val craneSteps = craneStepsInput.mapNotNull { it.toCraneSteps() }
        craneSteps.forEach { step ->
            if (newCrane) {
                val move = stacks[step.from].takeLast(step.moves)
                stacks[step.from] = stacks[step.from].dropLast(step.moves).toMutableList()
                stacks[step.to].addAll(move)
            } else {
                repeat(step.moves) {
                    stacks[step.to].add(stacks[step.from].removeLast())
                }
            }
        }
        return stacks.mapNotNull { it.lastOrNull() }.joinToString(separator = "")
    }

    val input = readInput("Day05_input")
    println(part1(input, newCrane = false))
    println(part1(input, newCrane = true))
}


fun splitInput(input: List<String>): Pair<List<String>, List<String>> {
    val lineSeparator = input.indexOfFirst { it.isEmpty() }
    return input.subList(0, lineSeparator) to input.subList(lineSeparator, input.size)
}

fun String.toCraneSteps(): CraneSteps? {
    return "\\w+\\s(\\d*)\\s\\w+\\s(\\d*)\\s\\w+\\s(\\d*)".toRegex().find(this)?.groupValues?.drop(1)?.takeIf { it.size == 3 }?.map { it.toInt() }
        ?.let {
            CraneSteps(it.first(), it[1]-1, it.last()-1)
        }
}

data class CraneSteps(val moves: Int, val from: Int, val to: Int)