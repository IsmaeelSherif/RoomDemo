package hazem.projects.roomdemo.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;

// TODO 2.1 - Create the entity
@Entity(tableName = "tasks_table")
public class Task {

    //TODO 2.2 - Choose primary key
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String notes;

    //TODO 2.3 - Configure the attributes
    private boolean isChecked;

    //TODO 2.4 - Choose the constructor

    @Ignore
    public Task(String title, String notes) {
        this.title = title;
        this.notes = notes;
    }

    @Ignore
    public Task(String title, String notes, boolean isChecked) {
        this(title,notes);
        this.isChecked = isChecked;
    }

    public Task(long id, String title, String notes, boolean isChecked) {
        this(title, notes, isChecked);
        this.id = id;
    }


    /*
        Getters & Setters
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    //TODO 2.5 - Generate equals method
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Task) {
            Task comparedTask = (Task) obj;
            return id == comparedTask.getId()
                    && isChecked == comparedTask.isChecked()
                    && title.equals(comparedTask.getTitle())
                    && notes.equals(comparedTask.getNotes());
        }
        return false;
    }
}
