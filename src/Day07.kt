fun main() {
    val rootMap = mutableMapOf<String, Int>()
    var currentDir = ""

    fun enterDirectory(dirName: String) {
        currentDir = when {
            currentDir.isEmpty() -> dirName
            currentDir == "/" -> "/$dirName"
            else -> "$currentDir/$dirName"
        }
        rootMap[currentDir] = rootMap.getOrDefault(currentDir, 0)
    }

    fun endDirectory() {
        currentDir = currentDir.removeSuffix("/").substringBeforeLast('/')
        if (currentDir.isEmpty()) {
            currentDir = "/"
        }
    }

    fun parseFileContent(file: String) {
        rootMap[currentDir] = rootMap.getOrDefault(currentDir, 0) + file.split(" ")[0].toInt()
    }

    val input = readInput("Day07_input")
    input.forEach {
        when {
            it.startsWith("$ cd ..") -> endDirectory()
            it.startsWith("$ cd ") -> enterDirectory(it.removePrefix("$ cd "))
            it.endsWith(" ls") -> Unit
            it.take(1).toIntOrNull() != null -> parseFileContent(it)
        }
    }

    val directorySizeMap = rootMap.mapValues { (dir) -> rootMap.filter { it.key.startsWith(dir) }.values.sum() }
    val sumOfDirectoryOfMaxSize100000 = directorySizeMap.filterValues { it <= 100000 }.values.sum()
    println(sumOfDirectoryOfMaxSize100000)

    val totalAvailableDiskSpace = 70000000
    val spaceNeededForUpdate = 30000000
    val currentFreeSpace = totalAvailableDiskSpace - directorySizeMap["/"]!!
    val spaceToFree = spaceNeededForUpdate - currentFreeSpace
    val directoryToDelete = directorySizeMap.toList().sortedBy { it.second }
        .first { it.second >= spaceToFree }
    println(directoryToDelete.second)

}