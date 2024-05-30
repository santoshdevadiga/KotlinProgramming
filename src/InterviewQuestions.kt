fun main() {

    val n=4
    println( "Factorial of $n is ${factorialOfNumber(n)}")

    val number = 29
    println("$number is ${if (isPrime(number)) "a prime" else "not a prime"} number.")


    val fn = 10
    println("Fibonacci series up to $fn terms: ${fibonacciIterative(fn)}")

    val findBGN = 529
    println("The maximum binary gap for $findBGN is: ${maxBinaryGap(findBGN)}")

    val numbers = arrayOf(1, 2, 3, 4, 5, 6)
    println("Sum of squares of even numbers: ${sumOfSquaresOfEvenNumbers(numbers)}")
}

fun factorialOfNumber(n: Int): Int {
    return when (n) {
        0 -> 1
        else -> (1..n).reduce { acc, i -> acc * i }
    }
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..n / 2) {
        if (n % i == 0) return false
    }
    return true
}

fun fibonacciIterative(n: Int): List<Int> {
    if (n <= 0) return emptyList()
    if (n == 1) return listOf(0)

    val fibonacciList = mutableListOf(0, 1)
    for (i in 2..<n) {
        fibonacciList.add(fibonacciList[i - 1] + fibonacciList[i - 2])
    }
    return fibonacciList
}

fun maxBinaryGap(n: Int): Int {
    val binaryString = n.toString(2) // Convert number to binary string
    var maxGap = 0
    var currentGap = 0
    var inGap = false

    for (char in binaryString) {
        when (char) {
            '1' -> {
                if (inGap) {
                    maxGap = maxOf(maxGap, currentGap)
                    currentGap = 0
                }
                inGap = true
            }
            '0' -> {
                if (inGap) {
                    currentGap++
                }
            }
        }
    }

    return maxGap
}

//
fun sumOfSquaresOfEvenNumbers(arr: Array<Int>): Int {
    return arr.filter { it % 2 == 0 } // Filter even numbers
        .map { it * it } // Square each even number
        .sum() // Sum the squares
}