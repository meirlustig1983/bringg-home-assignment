package bringg.home.assignment.filters;

import bringg.home.assignment.dto.Task;

import java.util.List;

public interface TasksFilter {
    List<Task> filter(List<Task> tasks, String[][] distanceMatrix);
}
