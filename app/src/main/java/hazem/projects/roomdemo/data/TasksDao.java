package hazem.projects.roomdemo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//TODO 3 - DAO Class
@Dao
public interface TasksDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("delete from tasks_table")
    void deleteAll();

    @Query("select * from tasks_table where id = :id")
    LiveData<Task> get(long id);

    @Query("select * from tasks_table order by id desc")
    LiveData<List<Task>> getAll();

}
