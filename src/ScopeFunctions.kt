fun main() {

    //Let
    usageOfLet()

    //run
    usageOfRun()

    //with
    usageOfWith()

    //apply
    usageOfApply()

    //also
    usageOfAlso()

}


fun usageOfLet() {
    println("===================================Let===================================")
    /*
    The Kotlin standard library function let can be used for scoping and null-checks.
    When called on an object, let executes the given block of code and returns the result of its last expression.
    The object is accessible inside the block by the reference it (by default) or a custom name.

    */
    val empty = "test".let {               // Calls the given block on the result on the string "test".
        println(it)                        // Calls the function on "test" by the it reference.
        it.isEmpty()                       // let returns the value of this expression.
    }
    println("is empty: $empty")

    fun printNonNull(str: String?) {
        println("Printing \"$str\":")
        str?.let {                         // Uses safe call, so let and its code block will be executed only on non-null values.
            print("\t")
            println(it)
            println()
        }
    }

    fun printIfBothNonNull(strOne: String?, strTwo: String?) {
        strOne?.let { firstString ->       // Uses the custom name instead of it, so that the nested let can access the context object of the outer let.
            strTwo?.let { secondString ->
                println("$firstString : $secondString")
                println()
            }
        }
    }

    printNonNull(null)
    printNonNull("my string")
    printIfBothNonNull("First", "Second")


}

fun usageOfRun() {
    println("===================================Run===================================")
    /*
    Like let, run is another scoping function from the standard library.
    Basically, it does the same: executes a code block and returns its result.
    The difference is that inside run the object is accessed by this.
    This is useful when you want to call the object's methods rather than pass it as an argument.
    */
    fun getNullableLength(ns: String?) {
        println("for \"$ns\":")
        ns?.run {                                                  // 1
            println("\tis empty? " + isEmpty())                    // 2
            println("\tlength = $length")
            length                                                 // 3
        }
    }
    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")
}

fun usageOfWith() {
    println("===================================With===================================")
    /*
    with is a non-extension function that can access members of its argument concisely:
    you can omit the instance name when referring to its members.
    */
    val configuration = object {
        val host = "127.0.0.1"
        val port = "9000"
    }
    with(configuration) {
        println("$host:$port")
    }
// instead of:
    println("${configuration.host}:${configuration.port}")

}

fun usageOfAlso() {

    /*
    also works like apply: it executes a given block and returns the object called.
    Inside the block, the object is referenced by it, so it's easier to pass it as an argument.
    This function is handy for embedding additional actions, such as logging in call chains.
    */
    val jake =
        Person("Test User", 44, "Android developer")   // Creates a Person() object with the given property values.
            .also {                                                     // Applies the given code block to the object. The return value is the object itself.
                writeCreationLog(it)                                    // Calls the logging function passing the object as an argument.
            }
}

fun usageOfApply() {
    println("===================================Apply===================================")
    /*
    apply executes a block of code on an object and returns the object itself.
    Inside the block, the object is referenced by this.
    This function is handy for initializing objects.
    */
    val jake = Person("J", 12, "Test")    // Creates a Person() instance with default property values.
    println("Before Apply Person Name : ${jake.name} Age : ${jake.age} About : ${jake.about}")
    val Description = jake.apply {                 // Applies the code block (next 3 lines) to the instance.
        name = "Jake"                              // Inside apply, it's equivalent to jake.name = "Jake".
        age = 30
        about = "Android developer"
    }
    println("Description Object Person Name : ${Description.name} Age : ${Description.age} About : ${Description.about}")
    println("After Apply Person Name : ${jake.name} Age : ${jake.age} About : ${jake.about}")
}

class Person(var name: String, var age: Int, var about: String) {

}

fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created.")
}

