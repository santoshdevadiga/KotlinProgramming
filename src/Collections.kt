import kotlin.math.abs

fun main(){

    listUsage()

    setUsage()

    mapUsage()

    filterUsage()

    mapExtenstion()

    anyAllNone()

    findFindLast()

    firstLast()

    countSorted()

    associateBygroupBy()

    partition()

    flatMap()

    minOrNullmaxOrNull()

    zip()

    getOrElse()

    mapElementAccess()

}

fun listUsage(){

    /*
    A list is an ordered collection of items.
    In Kotlin, lists can be either mutable (MutableList) or
    read-only (List). For list creation, use the standard library functions listOf()
    for read-only lists and mutableListOf() for mutable lists.
    To prevent unwanted modifications, obtain read-only views of mutable lists by casting them to List.
    */
    val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // Creates a MutableList.
    val sudoers: List<Int> = systemUsers                              // Creates a read-only view of the list.

    fun addSystemUser(newUser: Int) {                                 // Adds a new item to the MutableList.
        systemUsers.add(newUser)
    }

    fun getSysSudoers(): List<Int> {                                  // A function that returns an immutable List.
        return sudoers
    }

    addSystemUser(4)                                      // Updates the MutableList. All related read-only views are updated as well since they point to the same object.
    println("Total User: ${getSysSudoers().size}")                // Retrieves the size of the read-only list.
    getSysSudoers().forEach {                                     // Iterates the list and prints its elements.
            i -> println("User ID : $i")
    }
    // getSysSudoers().add(5)                            // Attempting to write to the read-only view causes a compilation error.
}

fun setUsage(){
    /*
    A set is an unordered collection that does not support duplicates.
    For creating sets, there are functions setOf() and mutableSetOf().
    A read-only view of a mutable set can be obtained by casting it to Set.
    */
    val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3") // Creates a Set with given elements.

    fun addIssue(uniqueDesc: String): Boolean {
        return openIssues.add(uniqueDesc)                                                             // Returns a boolean value showing if the element was actually added.
    }

    fun getStatusLog(isAdded: Boolean): String {
        return if (isAdded) "registered correctly." else "marked as duplicate and rejected."          // Returns a string, based on function input parameter.
    }

    val aNewIssue: String = "uniqueDescr4"
    val anIssueAlreadyIn: String = "uniqueDescr2"

    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")                              // Prints a success message: the new element is added to the Set.
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")                // Prints a failure message: the element can't be added because it duplicates an existing element.
}
const val POINTS_X_PASS: Int = 15
fun mapUsage(){
    /*
    A map is a collection of key/value pairs, where each key is unique
    and is used to retrieve the corresponding value.
    For creating maps, there are functions mapOf() and mutableMapOf().
    Using the to infix function makes initialization less noisy.
    A read-only view of a mutable map can be obtained by casting it to Map.
    */

    val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // Creates a mutable Map.
    val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // Creates a read-only view of the Map.

    fun updatePointsCredit(accountId: Int) {
        if (EZPassAccounts.containsKey(accountId)) {                                        // Checks if the Map's key exists.
            println("Updating $accountId...")
            EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // Reads the corresponding value and increments it with a constant value.
        } else {
            println("Error: Trying to update a non-existing account (id: $accountId)")
        }
    }

    fun accountsReport() {
        println("EZ-Pass report:")
        EZPassReport.forEach {                                                              // Iterates immutable Map and prints key/value pairs.
                k, v -> println("ID $k: credit $v")
        }
    }

    accountsReport()                                                                    // Reads the account points balance, before updates.
    updatePointsCredit(1)                                                     // Updates an existing account two times.
    updatePointsCredit(1)
    updatePointsCredit(5)                                                     // Tries to update a non-existing account: prints an error message.
    accountsReport()                                                                    // Reads the account points balance, after updates.
}

fun filterUsage(){
    /*
    filter function enables you to filter collections.
    It takes a filter predicate as a lambda-parameter.
    The predicate is applied to each element.
    Elements that make the predicate true are returned in the result collection.
    */
    val numbers = listOf(1, -2, 3, -4, 5, -6)      // Defines collection of numbers.

    val positives = numbers.filter { x -> x > 0 }  // Gets positive numbers.

    val negatives = numbers.filter { it < 0 }      // Uses the shorter it notation to get negative numbers.

    println("Positive Numbers are $positives")
    println("Negative Numbers are $negatives")
}

fun mapExtenstion(){
    /*
    map extension function enables you to apply a transformation to all elements in a collection.
    It takes a transformer function as a lambda-parameter.
    */

    val numbers = listOf(1, -2, 3, -4, 5, -6)     // Defines a collection of numbers.

    val doubled = numbers.map { x -> x * 2 }      // Doubles numbers.

    val tripled = numbers.map { it * 3 }          // Uses the shorter it notation to triple the numbers.

    println("Number List : $numbers")
    println("Doubled List : $doubled")
    println("Tripled List : $tripled")
}

fun anyAllNone(){
    /*
    These functions check the existence of collection elements that match a given predicate.
    any - Function any returns true if the collection contains at least one element that matches the given predicate.
    all - Function all returns true if all elements in collection match the given predicate.
    none - Function none returns true if there are no elements that match the given predicate in the collection.
    */

    val numbers = listOf(1, -2, 3, -4, 5, -6)            // Defines a collection of numbers.

    val anyNegative = numbers.any { it < 0 }             // Checks if there are negative elements.

    val anyGT6 = numbers.any { it > 6 }                  // Checks if there are elements greater than 6.

    val allEven = numbers.all { it % 2 == 0 }            // Checks whether all elements are even.

    val allLess6 = numbers.all { it < 6 }                // Checks whether all elements are less than 6.

    val noneOdd = numbers.none { it % 2 == 1 }           // Checks if there are no odd elements (all elements are even).

    val noneGT6 = numbers.none { it > 6 }               // Checks if there are no elements greater than 6.

    println("Any Negative : $anyNegative")
    println("Any anyGT6 : $anyGT6")
    println("All allEven : $allEven")
    println("All allLess6 : $allLess6")
    println("None noneOdd : $noneOdd")
    println("None noneGT6 : $noneGT6")


}

fun findFindLast(){
    /*
    The find and findLast functions return the first or the last collection element that matches the given predicate.
    If there are no such elements, these functions return null.
    */

    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // Defines a collection of words.

    val first = words.find { it.startsWith("some") }                                // Looks for the first word starting with "some".
    val last = words.findLast { it.startsWith("some") }                             // Looks for the last word starting with "some".
    val nothing = words.find { it.contains("nothing") }                             // Looks for the first word containing "nothing".

    println("Find First :: $first")
    println("Find Last :: $last")
}

fun firstLast(){
    /*
    These functions return the first and the last element of the collection correspondingly.
    You can also use them with a predicate; in this case, they return the first or
    the last element that matches the given predicate.
    If a collection is empty or doesn't contain elements matching the predicate,
    the functions throw NoSuchElementException.
    */

    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val first = numbers.first()                          // Picks the first element.
    val last = numbers.last()                            // Picks the last element.

    val firstEven = numbers.first { it % 2 == 0 }        // Picks the first even element.
    val lastOdd = numbers.last { it % 2 != 0 }           // Picks the last odd element.

    println("First item : $first")
    println("Last item : $last")
    println("First Even item : $firstEven")
    println("Last Odd item : $lastOdd")

    /*
    Below functions work almost the same way with one difference: they return null if there are no matching elements.
    */
    val words = listOf("foo", "bar", "baz", "faz")
    val empty = emptyList<String>()

    val firstornull = empty.firstOrNull()                        // Picks the first element from empty collection. It supposed to be null.
    val lastornull = empty.lastOrNull()                          // Picks the last element from empty collection. It supposed to be null as well.

    val firstF = words.firstOrNull { it.startsWith('f') }  // Picks the first word starting with 'f'.
    val firstZ = words.firstOrNull { it.startsWith('z') }  // Picks the first word starting with 'z'.
    val lastF = words.lastOrNull { it.endsWith('f') }      // Picks the last word ending with 'f'.
    val lastZ = words.lastOrNull { it.endsWith('z') }      // Picks the last word ending with 'z'.

    println("FirstorNull :: $firstornull")
    println("Lastornull :: $lastornull")
    println("firstF :: $firstF")
    println("firstF :: $firstZ")
    println("lastF :: $lastF")
    println("lastZ :: $lastZ")

}

fun countSorted(){

    /*
    count functions returns either the total number of elements in a collection or the number of elements matching the given predicate.
    */
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val totalCount = numbers.count()                     // Counts the total number of elements.
    val evenCount = numbers.count { it % 2 == 0 }        // Counts the number of even elements.

    println("Total number of elements: $totalCount")
    println("Number of even elements: $evenCount")

    /*
    sorted returns a list of collection elements sorted according to their natural sort order (ascending).
    sortedBy sorts elements according to natural sort order of the values returned by specified selector function.
    */
    val shuffled = listOf(5, 4, 2, 1, 3, -10)
    val natural = shuffled.sorted()                             // Sorts it in the natural order.
    val inverted = shuffled.sortedBy { -it }                    // Sorts it in the inverted natural order by using -it as a selector function.
    val descending = shuffled.sortedDescending()                // Sorts it in the inverted natural order by using sortedDescending.
    val descendingBy = shuffled.sortedByDescending { abs(it)  } // Sorts it in the inverted natural order of items' absolute values by using abs(it) as a selector function.

    println("Shuffled: $shuffled")
    println("Natural order: $natural")
    println("Inverted natural order: $inverted")
    println("Inverted natural order value: $descending")
    println("Inverted natural order of absolute values: $descendingBy")

}

data class UserData(val name: String, val city: String, val phone: String)

fun associateBygroupBy(){

    /*
    Functions associateBy and groupBy build maps from the elements of a collection indexed by the specified key.
    The key is defined in the keySelector parameter.
    You can also specify an optional valueSelector to define what will be stored in the value of the map element.
    The difference between associateBy and groupBy is how they process objects with the same key:
    associateBy uses the last suitable element as the value.
    groupBy builds a list of all suitable elements and puts it in the value.
    The returned map preserves the entry iteration order of the original collection.
    */

    val people = listOf(                                                     // 2
        UserData("John", "Boston", "+1-888-123456"),
        UserData("Sarah", "Munich", "+49-777-789123"),
        UserData("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        UserData("Vasilisa", "Saint-Petersburg", "+7-999-123456"))

    val phoneBook = people.associateBy { it.phone }                          // Builds a map from phone numbers to their owners' information. it.phone is the keySelector here. The valueSelector is not provided, so the values of the map are UserData objects themselves.
    val cityBook = people.associateBy(UserData::phone, UserData::city)           // Builds a map from phone numbers to the cities where owners live. UserData::city is the valueSelector here, so the values of the map contain only cities.
    val peopleCities = people.groupBy(UserData::city, UserData::name)            // Builds a map that contains cities and people living there. The values of the map are lists of userdata names.
    val lastPersonCity = people.associateBy(UserData::city, UserData::name)      // Builds a map that contains cities and the last user living there. The values of the map are names of the last user living in each city.

    println("People: $people")
    println("Phone book: $phoneBook")
    println("City book: $cityBook")
    println("People living in each city: $peopleCities")
    println("Last person living in each city: $lastPersonCity")

}

fun partition(){

    /*
    The partition function splits the original collection into a pair of lists using a given predicate:
            Elements for which the predicate is true.
            Elements for which the predicate is false.
    * */

    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val evenOdd = numbers.partition { it % 2 == 0 }           // Splits numbers into a Pair of lists with even and odd numbers.
    val (positives, negatives) = numbers.partition { it > 0 } // Splits numbers into two lists with positive and negative numbers. Pair destructuring is applied here to get the Pair members.


    println("Numbers: $numbers")
    println("Even numbers: ${evenOdd.first}")
    println("Odd numbers: ${evenOdd.second}")
    println("Positive numbers: $positives")
    println("Negative numbers: $negatives")
}

fun flatMap(){
    /*
    flatMap transforms each element of a collection into an iterable object and
    builds a single list of the transformation results.
    The transformation is user-defined.
    */
    val fruitsBag = listOf("apple","orange","banana","grapes")  // 1
    val clothesBag = listOf("shirts","pants","jeans")           // 2
    val cart = listOf(fruitsBag, clothesBag)                    // Adds fruitsBag and clothesBag to the cart list.
    val mapBag = cart.map { it }                                // Builds a map of cart elements, which is a list of two lists.
    val flatMapBag = cart.flatMap { it }                        // Builds a flatMap of cart elements, which is a single list consisting of elements from both lists.
    println("Your bags are: $mapBag")
    println("The things you bought are: $flatMapBag")

}

fun minOrNullmaxOrNull(){

    /*
    minOrNull and maxOrNull functions return the smallest and the largest element of a collection.
    If the collection is empty, they return null.
    */
    val numbers = listOf(1, 2, 3)
    val empty = emptyList<Int>()
    val only = listOf(3)

    println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}") // For non-empty collection, functions return the smallest and the largest element.
    println("Empty: $empty, min = ${empty.minOrNull()}, max = ${empty.maxOrNull()}")        // For empty collections, both functions return null.
    println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")            // For collection with only one element, both functions return the same value.
}

fun zip(){

    /*
    zip function merges two given collections into a new collection.
    By default, the result collection contains Pairs of source collection elements with the same index.
    However, you can define your own structure of the result collection element.
    The size of the result collection equals to the minimum size of a source collection.
    */
    val A = listOf("a", "b", "c")                  // 1
    val B = listOf(1, 2, 3, 4)                     // 1

    val resultPairs = A zip B                      // 2
    val resultReduce = A.zip(B) { a, b -> "$a$b" } // 3


    println("A to B: $resultPairs")
    println("\$A\$B: $resultReduce")
}

fun getOrElse(){

    /*
    getOrElse provides safe access to elements of a collection.
    It takes an index and a function that provides the default value in cases when the index is out of bound.
    */

    val list = listOf(0, 10, 20)
    println(list.getOrElse(1) { 42 })    // Prints the element at the index 1.
    println(list.getOrElse(10) { 42 })   // Prints 42 because the index 10 is out of bounds.


    //getOrElse can also be applied to Map to get the value for the given key.

    val map = mutableMapOf<String, Int?>()
    println(map.getOrElse("x") { 1 })       // Prints the default value because the key "x" is not in the map.

    map["x"] = 3
    println(map.getOrElse("x") { 1 })       // Prints 3, the value for the key "x".

    map["x"] = null
    println(map.getOrElse("x") { 1 })       // Prints the default value because the value for the key "x" is not defined.
}

fun mapElementAccess(){

    /*
        When applied to a map, [] operator returns the value corresponding to the given key,
        or null if there is no such key in the map.
        getValue function returns an existing value corresponding to the given key or throws an exception
        if the key wasn't found. For maps created with withDefault,
        getValue returns the default value instead of throwing an exception.
    */

    val map = mapOf("key" to 42)

    val value1 = map["key"]                                     // Returns 42 because it's the value corresponding to the key "key".
    val value2 = map["key2"]                                    // Returns null because "key2" is not in the map.

    val value3: Int = map.getValue("key")                       // Returns 42 because it's the value corresponding to the key "key".

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key2")                // Returns the default value because "key2" is absent. For this key it's 4.

    try {
        map.getValue("anotherKey")                              // Throws NoSuchElementException because "anotherKey" is not in the map.
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }

    println("value1 is $value1")
    println("value2 is $value2")
    println("value3 is $value3")
    println("value4 is $value4")
}

