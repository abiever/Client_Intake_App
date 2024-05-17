package cherry.company.client_intake_app.repos

import cherry.company.client_intake_app.Client
import java.util.*
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.toRealmList

class ClientRepo {

    lateinit var realm: Realm

    //TODO: Figure out if the below should be like below or formatted like in the tutorial here:
    //https://www.mongodb.com/developer/products/realm/getting-started-kmm-flexiable-sync/#setting-up-your-environment
    suspend fun saveClient(_id: String, firstName: String, lastName: String, birthDate: String, initialPain: String, initialHealthIssues: List<String> ) {
        val client = Client(
            _id = UUID.randomUUID().toString(), //This may need to be refactored to either not be here or explicitly turned into an id?
            firstName,
            lastName,
            birthDate,
            initialPain,
            initialHealthIssues.toRealmList()
        )

        realm.write {
            copyToRealm(client)
        }

        //TODO: I'm pretty close to figuring out a solution to try and compile; just need to continue with something like below:
        //TODO: Follow the tutorial along with his Github!!
//        private suspend fun setupRealmSync() {
//            val user = appServiceInstance.login(Credentials.anonymous())
//            val config = SyncConfiguration
//                .Builder(user, setOf(QueryInfo::class))
//                .initialSubscriptions { realm ->
//                    // information about the data that can be read or modified.
//                    add(
//                        query = realm.query<QueryInfo>(),
//                        name = "subscription name",
//                        updateExisting = true
//                    )
//                }
//                .build()
//            realm = Realm.open(config)
//        }

    }

}