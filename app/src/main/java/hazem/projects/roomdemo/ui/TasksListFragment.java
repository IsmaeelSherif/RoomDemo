package hazem.projects.roomdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import hazem.projects.roomdemo.R;
import hazem.projects.roomdemo.data.TasksRepository;
import hazem.projects.roomdemo.databinding.FragmentTasksListBinding;

public class TasksListFragment extends Fragment {

    private FragmentTasksListBinding binding;

    @Override
    public View onCreateView
            (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();

        binding.addButton.setOnClickListener(v->{
            NavHostFragment.findNavController(TasksListFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    private void initRecyclerView() {
        TasksRepository repository = TasksRepository.getInstance(getContext());

        TasksRecyclerAdapter adapter = new TasksRecyclerAdapter(getContext());

        // TODO 6.1 - Retrieve tasks using LiveData
        repository.getAll().observe(getViewLifecycleOwner(), tasks -> {
            adapter.setTasks(tasks);
        });

        binding.tasksRecyclerView.setAdapter(adapter);
        binding.tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}