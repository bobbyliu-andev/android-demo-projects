package club.bobbychangliu.base.database.firebase

import club.bobbychangliu.base.database.Agent
import club.bobbychangliu.base.utils.toast
import com.google.firebase.auth.FirebaseAuth

object FireAuth {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

	lateinit var mAgent: Agent

	fun signIn(username: String, pwd: String, onResultListener: (isSuccessful: Boolean) -> Unit) {
		auth.signInWithEmailAndPassword(username, pwd).addOnSuccessListener {
			FireAuth.updateUser {
				isSuccessful ->
				onResultListener(isSuccessful)
			}
		}.addOnFailureListener {
			it.printStackTrace()
			toast(it.localizedMessage)
			onResultListener(false)
		}
	}

	fun updateUser(onResultListener: (isSuccessful: Boolean) -> Unit) {

		val uid = auth.currentUser?.uid
		if (uid == null) {
			toast("User is not logged in. Please login first.")
			return
		}

		FirestoreUtil.firestore.collection("agents")
				.document(uid)
				.get()
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						task.result.toObject(Agent::class.java)?.let { agent ->
							mAgent = agent
							onResultListener(true)
						}
					} else {
						toast("Failed to load user info...")
					}
				}
	}

}