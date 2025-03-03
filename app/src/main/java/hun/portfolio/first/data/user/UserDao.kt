package hun.portfolio.first.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getUser(id: String): UserEntity?

    @Insert
    suspend fun addUser(user: UserEntity)
}
