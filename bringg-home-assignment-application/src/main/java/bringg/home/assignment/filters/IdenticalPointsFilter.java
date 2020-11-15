package bringg.home.assignment.filters;

import bringg.home.assignment.dto.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class IdenticalPointsFilter implements TasksFilter {

    @Override
    public List<Task> filter(List<Task> tasks, String[][] distanceMatrix) {
        List<Task> returnArr = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.getPointA().getId() != task.getPointB().getId()) {
                returnArr.add(task);
            } else {
                log.info("the distance between two identical points is zero, the task is redundant. pointId: {}", task.getPointA().getId());
                distanceMatrix[task.getPointA().getId()][task.getPointB().getId()] = "0.00";
            }
        });
        return returnArr;
    }
}