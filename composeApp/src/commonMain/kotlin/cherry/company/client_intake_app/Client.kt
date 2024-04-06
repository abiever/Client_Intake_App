package cherry.company.client_intake_app

class Client(private var firstName: String, private var lastName: String, private var age: Int) {
    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getAge(): Int {
        return age
    }
}