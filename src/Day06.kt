fun main() {

    fun part(input: String, length: Int): Int {
        input.forEachIndexed { index, _ ->
            val group = input.subSequence(index, index + length)
            if (group.all { charInGroup -> group.count { charInGroup == it } == 1 }) {
                return index + length
            }
        }
        return 0
    }

    val input = readInput("Day06_input").first()
    println(part(input, 4))
    println(part(input, 14))
}