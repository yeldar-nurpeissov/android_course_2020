package com.example.room.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.room.db.entity.AccessLevel
import com.example.room.db.entity.MyUserTable
import com.example.room.db.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(users: List<User>)

    @Query("SELECT * FROM $MyUserTable WHERE accessLevel = :accessLevel")
    fun getUsersWithAccessLevel(accessLevel: AccessLevel): List<User>

    @Query("SELECT * FROM $MyUserTable WHERE accessLevel = :accessLevel LIMIT 1")
    fun getUserWithAccessLevel(accessLevel: AccessLevel): User

    @Query("SELECT * FROM $MyUserTable WHERE accessLevel = :accessLevel")
    fun getUserWithAccessLevelFlow(accessLevel: AccessLevel): Flow<User?>

    @Query("SELECT * FROM $MyUserTable WHERE id > :fromId LIMIT :limit")
    fun getUserNewest(fromId: Int, limit: Int): List<User>

    @Query("SELECT * FROM $MyUserTable WHERE id < :fromId LIMIT :limit")
    fun getUserOldest(fromId: Int, limit: Int): List<User>

    @Query("SELECT * FROM $MyUserTable")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM $MyUserTable ORDER BY id ASC LIMIT 1")
    fun getOldestUser(): User?

    @Query("SELECT * FROM $MyUserTable")
    fun getAllUsersByPage(): DataSource.Factory<Int, User>

    @Query("SELECT count(*) FROM $MyUserTable")
    fun getUsersCount(): Int

    @Query("UPDATE $MyUserTable SET accessLevel = :accessLevel WHERE id in (:ids)")
    fun updateAccessLevel(ids: List<Int>, accessLevel: AccessLevel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(user: User): Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(users: List<User>)

    @Query("DELETE FROM $MyUserTable")
    fun deleteAll()

    @Query("DELETE FROM $MyUserTable WHERE accessLevel = :accessLevel")
    fun deleteWithAccessLevel(accessLevel: AccessLevel)

    @Delete
    fun delete(user: User)

    @Delete
    fun delete(users: List<User>)
}