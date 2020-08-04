package com.example.room.db

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PositionalDataSource
import com.example.room.db.dao.UserDao
import com.example.room.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserItemKeyedDataSource(
    private val userDao: UserDao
) : ItemKeyedDataSource<Int, User?>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<User?>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val users = userDao.getUserNewest(
                params.requestedInitialKey ?: 0,
                params.requestedLoadSize
            )
            callback.onResult(users)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<User?>) {
        GlobalScope.launch(Dispatchers.IO) {
            val users = userDao.getUserNewest(
                params.key, params.requestedLoadSize
            )
            callback.onResult(users)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<User?>) {
        GlobalScope.launch(Dispatchers.IO) {
            val users = userDao.getUserOldest(
                params.key,
                params.requestedLoadSize
            )
            callback.onResult(users)
        }
    }

    override fun getKey(item: User): Int {
        return item.id
    }

    //   private final EmployeeStorage employeeStorage;
    //
    //   public MyPositionalDataSource(EmployeeStorage employeeStorage) {
    //       this.employeeStorage = employeeStorage;
    //   }
    //
    //   @Override
    //   public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Employee> callback) {
    //       Log.d(TAG, "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
    //               ", requestedLoadSize = " + params.requestedLoadSize);
    //       EmployeeData result = employeeStorage.getInitialData(params.requestedStartPosition, params.requestedLoadSize);
    //       callback.onResult(result.data, result.position);
    //   }
    //
    //   @Override
    //   public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Employee> callback) {
    //       Log.d(TAG, "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize);
    //       List<Employee> result = employeeStorage.getData(params.startPosition, params.loadSize);
    //       callback.onResult(result);
    //   }
}
