package hun.portfolio.first.data.user.impl

import hun.portfolio.first.data.user.UserDao
import hun.portfolio.first.data.user.UserEntity
import hun.portfolio.first.data.user.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }

    override suspend fun addUser(id: String, name: String): Boolean {
        if (!isExist(id)) {
            val user = UserEntity(id, name)

            userDao.addUser(user)

            return true
        }

        return false
    }

    override suspend fun isExist(id: String): Boolean {
        val user = userDao.getUser(id)

        return user != null
    }
}
