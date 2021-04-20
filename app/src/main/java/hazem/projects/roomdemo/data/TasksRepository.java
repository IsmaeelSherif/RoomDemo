package hazem.projects.roomdemo.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

//TODO 5 (Optional) - Repository Class
public class TasksRepository {

    Context context;
    TasksDao dao;

    private static TasksRepository sInstance;

    private TasksRepository(Context context){
        this.context = context;
        dao = TasksDatabase.getInstance(context).dao();
    }

    public static synchronized TasksRepository getInstance(Context context){
        if(sInstance == null){
            sInstance = new TasksRepository(context);
        }
        return sInstance;
    }

    public void insert(Task task){
        Thread thread = new Thread(()-> dao.insert(task));
        thread.start();
    }

    public void update(Task task){
        Thread thread = new Thread(()-> dao.update(task));
        thread.start();
    }

    public void delete(Task task){
        Thread thread = new Thread(() -> dao.delete(task));
        thread.start();
    }

    public void deleteAll(){
        Thread thread = new Thread(() -> dao.deleteAll());
        thread.start();
    }

    public Task get(long id){
        return dao.get(id).getValue();
    }

    public LiveData<List<Task>> getAll(){
        return dao.getAll();
    }

}
