package hun.portfolio.first.data.user

interface UserRepository {
    suspend fun getUser(id: String): UserEntity?
    suspend fun addUser(id: String, name: String): Boolean
    suspend fun isExist(id: String): Boolean
}
