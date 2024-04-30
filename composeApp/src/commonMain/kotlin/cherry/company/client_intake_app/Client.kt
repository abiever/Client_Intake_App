package cherry.company.client_intake_app

//TODO: Create ClientID member that is automatically created as a random number. This will be used to 'search' for each client when necessary
class Client(
    private var firstName: String,
    private var lastName: String,
    private var birthDate: String,
    private var initialPain: String, //or should this be Int?
    private var initialHealthIssues: List<String>
) {
    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getBirthDate(): String {
        return birthDate
    }

    fun getInitialPain(): String {
        return initialPain
    }

    fun getInitialHealthIssues(): String {
        return initialHealthIssues.joinToString(separator = ", ")
    }

    //TODO: Use this to help figure out how/why the related List isn't 'increasing' in size
    fun getInitialHealthIssuesSize(): Int {
        //Just using this as a sanity check
        return initialHealthIssues.size
    }

//    fun setInitialHealthIssues(healthIssues: List<String>) {
//        initialHealthIssues.clear()
//        initialHealthIssues.addAll(healthIssues)
//    }
}