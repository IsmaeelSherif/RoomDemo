package hazem.projects.roomdemo;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hazem.projects.roomdemo.data.Task;
import hazem.projects.roomdemo.data.TasksRepository;

public class AddNoteActivity extends AppCompatActivity {

    private TasksRepository repository;
    private FloatingActionButton saveButton;
    private EditText titleEditText;
    private EditText notesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        saveButton = findViewById(R.id.save_button);
        titleEditText = findViewById(R.id.title_editText);
        notesEditText = findViewById(R.id.notes_editText);

        repository = TasksRepository.getInstance(this);
        SaveNote();

    }

    private void SaveNote() {
        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String notes = notesEditText.getText().toString();
            Task task = new Task(title, notes);
            repository.insert(task);
            finish();
        });
    }
}