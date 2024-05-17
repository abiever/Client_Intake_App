package cherry.company.client_intake_app

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey

import org.mongodb.kbson.ObjectId


class Client( /*is 'open' needed here?*/
    @PrimaryKey /*Is this annotation allowed here?*/
    var _id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: String = "",
    var initialPain: String = "",
    var initialHealthIssues: RealmList<String> = realmListOf()
) : RealmObject {

//    @PrimaryKey
//    var _id: String = "" //This will be used by Realm and created automatically?
//    var firstName: String = ""
//    var lastName: String = ""
//    var birthDate: String = ""
//    var initialPain: String = ""//or should this be Int?
//    var initialHealthIssues: List<String> = listOf() //<String> here?

    //TODO: add a member for 'Notes'? (something for when memos and whatnot are added to a specific client

    //TODO: ****IMPORTANT**** I may need to add an 'empty constructor' here as per the Realm workaround solution (mentioned in below URL)
    //https://www.mongodb.com/docs/atlas/device-sdks/sdk/kotlin/realm-database/schemas/define-realm-object-model/#std-label-kotlin-define-object-model

    //constructor below (as mentioned above):
    constructor(): this("", "", "", "", "", realmListOf())

    //***NOTE: It turns out getters/setters have no need to be explicitly stated in Kotlin

//    fun getID(): String {
//        return _id
//    }
//
//    fun setID(_id: String) {
//        this._id = _id
//    }
//
//    fun getFirstName(): String {
//        return firstName
//    }
//
//    fun getLastName(): String {
//        return lastName
//    }
//
//    fun getBirthDate(): String {
//        return birthDate
//    }
//
//    fun getInitialPain(): String {
//        return initialPain
//    }
//
    fun getInitialHealthIssues(): String {
        return initialHealthIssues.joinToString(separator = ", ")
    }

}