package hazem.projects.roomdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hazem.projects.roomdemo.data.TasksRepository;
import hazem.projects.roomdemo.ui.TasksRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    TextView alertText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

//        setSupportActionBar(binding.toolbar);

        alertText = findViewById(R.id.alert_textView);

        initRecyclerView();

        findViewById(R.id.add_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });


    }

    private void initRecyclerView() {
        TasksRepository repository = TasksRepository.getInstance(this);

        RecyclerView tasksRecyclerView = findViewById(R.id.tasks_recyclerView);
        TasksRecyclerAdapter adapter = new TasksRecyclerAdapter(this);

        // TODO 6.1 - Retrieve tasks using LiveData
        repository.getAll().observe(this, tasks -> {
            adapter.setTasks(tasks);
            showAlert(tasks.isEmpty());
        });

        tasksRecyclerView.setAdapter(adapter);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void showAlert(boolean isEmpty){
        if (isEmpty)  alertText.setVisibility(View.VISIBLE);
        else alertText.setVisibility(View.GONE);
    }
}