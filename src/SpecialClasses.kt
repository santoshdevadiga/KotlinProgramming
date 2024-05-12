import java.util.Random

fun main(){

    dataClassUsage()

    enumClassUsage()

    sealedClassUage()

    objectClassUage()
}




fun dataClassUsage() {
    println()
    println("===================================Data Classes===================================")
    println()
    val user = User("Alex", 1)
    println(user)                                          // Method toString is auto-generated, which makes println output look nice.

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")   // Our custom equals considers two instances equal if their ids are equal.
    println("user == thirdUser: ${user == thirdUser}")

    // hashCode() function
    println(user.hashCode())                               // Data class instances with exactly matching attributes have the same hashCode.
    println(secondUser.hashCode())
    println(thirdUser.hashCode())

    // copy() function
    println(user.copy())                                   // Auto-generated copy function makes it easy to create a new instance.
    println(user === user.copy())                          // copy creates a new instance, so the object and its copy have distinct references.
    println(user.copy("Max"))                        // When copying, you can change values of certain properties. copy accepts arguments in the same order as the class constructor.
    println(user.copy(id = 3))                             // Use copy with named arguments to change the value despite of the properties order.

    println("name = ${user.component1()}")                 // Auto-generated componentN functions let you get the values of properties in the order of declaration.
    println("id = ${user.component2()}")
    println()
}


data class User(val name: String, val id: Int) {           // Defines a data class with the data modifier.
    override fun equals(other: Any?) =
        other is User && other.id == this.id               // Override the default equals method by declaring users equal if they have the same id.
}

fun enumClassUsage() {
    println()
    println("===================================Enum Classes===================================")
    println()

    /*
    Enum classes are used to model types that represent a finite set of distinct values,
    such as directions, states, modes, and so forth.
    */

    val state = State.RUNNING                         // Accesses an enum constant via the class name.
    val message = when (state) {                      // With enums, the compiler can infer if a when-expression is exhaustive so that you don't need the else-case.
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)

    //Enums may contain properties and methods like other classes,
    // separated from the list of enum constants by a semicolon.

    val red = Color.RED
    println(red)                                      // The default toString returns the name of the constant, here "RED".
    println(red.containsRed())                        // Calls a method on an enum constant.
    println(Color.BLUE.containsRed())                 // Calls a method via enum class name.
    println(Color.YELLOW.containsRed())               // The RGB values of RED and YELLOW share first bits (FF) so this prints 'true'.
    println()
}
enum class State {
    IDLE, RUNNING, FINISHED                           // Defines a simple enum class with three enum constants. The number of constants is always finite and they are all distinct.
}
enum class Color(val rgb: Int) {                      // Defines an enum class with a property and a method.
    RED(0xFF0000),                               // Each enum constant must pass an argument for the constructor parameter.
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // Enum class members are separated from the constant definitions by a semicolon.
}


fun sealedClassUage() {
    println()
    println("===================================Enum Classes===================================")
    println()
    /*
    Sealed classes let you restrict the use of inheritance.
    Once you declare a class sealed, it can only be subclassed from inside the same package where the sealed class is declared.
    It cannot be subclassed outside of the package where the sealed class is declared.
    */

    println(greetMammal(Cat("Snowy")))
    println()
}

sealed class Mammal(val name: String)                                                   // Defines a sealed class.

class Cat(val catName: String) : Mammal(catName)                                        // Defines subclasses. Note that all subclasses must be in the same package.
class Human(val humanName: String, val job: String) : Mammal(humanName)
fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // Uses an instance of the sealed class as an argument in a when expression.
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // A smartcast is performed, casting Mammal to Human.
        is Cat -> return "Hello ${mammal.name}"                                         // A smartcast is performed, casting Mammal to Cat.
    }                                                                                   // The else-case is not necessary here since all possible subclasses of the sealed class are covered. With a non-sealed superclass else would be required.
}


fun objectClassUage() {
    println()
    println("===================================Object Classes===================================")
    println()
    /*
    Classes and objects in Kotlin work the same way as in most object-oriented languages:
    a class is a blueprint, and an object is an instance of a class.
    Usually, you define a class and then create multiple instances of that class:
    */

    //object Expression
    rentPrice(10, 2, 1)  //Calls the function. This is when the object is actually created.

    //object Declaration
    DoAuth.takeParams("foo", "qwerty")   //Calls the method. This is when the object is actually created.

    //Companion Objects
    BigBen.getBongs(12)                             //Calls the companion object method via the class name.
    println()
}

fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //Creates a function with parameters.

    val dayRates = object {                                                     //Creates an object to use when calculating the result value.
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //Accesses the object's properties.

    println("Object Expression:: Total price: $$total")                                               //Prints the result.

}

object DoAuth {                                                 //Creates an object declaration.
    fun takeParams(username: String, password: String) {        //Defines the object method.
        println("Object Declaration:: input Auth parameters = $username:$password")
    }
}

class BigBen {                                  //Defines a class.
    companion object Bonger {                   //Defines a companion. Its name can be omitted.
        fun getBongs(nTimes: Int) {             //Defines a companion object method.
            print("Companion Object :: ")
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}







