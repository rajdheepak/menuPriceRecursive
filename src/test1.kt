
import java.util.Arrays
import kotlin.collections.ArrayList


fun main(args: Array<String>) {
    println("sum(" + Arrays.toString(sum_up(ArrayList(Arrays.asList(3, 9, 8, 4, 5, 7, 10)))[0].toTypedArray()) + ")=" + 15)
}

fun sum_up(numbers: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    val combinations = arrayListOf<ArrayList<Int>>()
    sum_up_recursive(numbers, arrayListOf(), combinations)
    return combinations
}

fun sum_up_recursive(numbers: ArrayList<Int>, partial: ArrayList<Int>, combinations: ArrayList<ArrayList<Int>>) {
    var s = 0
    for (x in partial) s += x

    if (s == 15) {
        combinations.add(partial)
    }
    if (s >= 15) {
        return
    }

    loop@ for (i in numbers.indices) {
        val remaining = ArrayList<Int>()
       for (j in i + 1 until numbers.size) {
            remaining.add(numbers[j])
        }
        val partial_rec = ArrayList(partial)
        partial_rec.add(numbers[i])
        sum_up_recursive(remaining, partial_rec, combinations)
    }
}