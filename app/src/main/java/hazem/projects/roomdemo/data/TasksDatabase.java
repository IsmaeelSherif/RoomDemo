package hazem.projects.roomdemo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//TODO 4 - Make the RoomDatabase Object
@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract TasksDao dao();

    private static TasksDatabase sInstance;

    public static synchronized TasksDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder
                    (context.getApplicationContext(), TasksDatabase.class, "tasks_database")
                    .fallbackToDestructiveMigration().build();
        }
        return sInstance;
    }

}
