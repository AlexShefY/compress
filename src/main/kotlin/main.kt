import java.io.File

fun encode(input : String) : String {
    if (input.isEmpty()) return ""

    val compressed = StringBuilder()
    var count = 1

    for (i in 1 until input.length) {
        if (input[i] == input[i - 1]) {
            count++
        } else {
            compressed.append("${input[i - 1]}±$count")
            count = 1
        }
    }

    compressed.append("${input[input.length - 1]}±$count")
    return compressed.toString()

}

fun decode(compressed : String) : String {
    if (compressed.isEmpty()) return ""

    val decompressed = StringBuilder()
    val parts = compressed.split("±")
    var i = 0

    while (i + 1 < parts.size) {
        val char = parts[i].last()
        var last = parts[i + 1].length - 1
        if (i + 2 == parts.size) {
            last++
        }
        val count = parts[i + 1].substring(0, last).toInt()
        repeat(count) {
            decompressed.append(char)
        }
        i++
    }

    return decompressed.toString()
}

fun main(args: Array<String>) {
    if (args.size != 5 || args[0] != "-c" && args[0] != "-d" || args[1] != "-i" || args[3] != "-o") {
        println("Please, enter the command")
        println("-c -- compress")
        println("-d -- decompress")
        println("-i -- input file")
        println("-o -- output file")
        return
    }
    val compress = args[0] == "-c"
    val input = File(args[2])
    if (!input.exists() || !input.isFile) {
        println("file ${args[2]} does not exist or is not a directory")
        return
    }
    val output = File(args[4])
    if (output.exists() && !output.isFile) {
        println("file ${args[4]} is not a directory")
        return
    }
    val out = mutableListOf<String>()
    input.forEachLine { line ->
        if (compress) {
            out.add(encode(line))
        } else {
            out.add(decode(line))
        }
    }
    output.writeText(out.joinToString("\n"))
}