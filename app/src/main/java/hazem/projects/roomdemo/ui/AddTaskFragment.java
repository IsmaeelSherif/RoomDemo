package hazem.projects.roomdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import hazem.projects.roomdemo.R;
import hazem.projects.roomdemo.data.Task;
import hazem.projects.roomdemo.data.TasksRepository;
import hazem.projects.roomdemo.databinding.FragmentAddTaskBinding;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;
    private TasksRepository repository;

    @Override
    public View onCreateView
            (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        repository = TasksRepository.getInstance(getContext());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SaveNote();
    }

    private void SaveNote() {
        binding.saveButton.setOnClickListener(v -> {
            String title = binding.titleEditText.getText().toString();
            String notes = binding.notesEditText.getText().toString();
            Task task = new Task(title, notes);
            repository.insert(task);
            NavHostFragment.findNavController(AddTaskFragment.this).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}