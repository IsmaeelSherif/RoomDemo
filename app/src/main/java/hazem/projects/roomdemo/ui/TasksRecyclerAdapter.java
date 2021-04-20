package hazem.projects.roomdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hazem.projects.roomdemo.R;
import hazem.projects.roomdemo.data.Task;
import hazem.projects.roomdemo.data.TasksRepository;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.TaskViewHolder> {

    private Context context;
    private TasksRepository repository;
    private List<Task> tasks = new ArrayList<>();

    public TasksRecyclerAdapter(Context context) {
        this.context = context;
        repository = TasksRepository.getInstance(context);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.recycler_row_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {


        private final CheckBox checkBox;
        private final TextView titleText;
        private final TextView notesText;
        private final ImageView deleteButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.row_task_check);
            titleText = itemView.findViewById(R.id.row_task_title);
            notesText = itemView.findViewById(R.id.row_task_notes);
            deleteButton = itemView.findViewById(R.id.row_task_delete);
        }


        public void bind(Task task, int position) {

            checkBox.setChecked(task.isChecked());
            titleText.setText(task.getTitle());

            if (!task.getNotes().isEmpty()) {
                notesText.setVisibility(View.VISIBLE);
                notesText.setText(task.getNotes());
            } else notesText.setVisibility(View.GONE);

            checkBox.setOnClickListener(view -> {
                task.setChecked(!task.isChecked());
                repository.update(task);
                checkBox.setChecked(task.isChecked());
            });

            deleteButton.setOnClickListener(view -> {
                repository.delete(task);
            });
        }

    }


}
