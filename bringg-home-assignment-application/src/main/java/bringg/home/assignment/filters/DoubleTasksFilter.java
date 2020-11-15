package bringg.home.assignment.filters;

import bringg.home.assignment.dto.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DoubleTasksFilter implements TasksFilter {

    @Override
    public List<Task> filter(List<Task> tasks, String[][] distanceMatrix) {
        List<Task> returnArr = new ArrayList<>();
        tasks.forEach(task -> {
            String value = distanceMatrix[task.getPointA().getId()][task.getPointB().getId()];
            if (value == null) {
                distanceMatrix[task.getPointA().getId()][task.getPointB().getId()] = "-1.00";
                distanceMatrix[task.getPointB().getId()][task.getPointA().getId()] = "-1.00";
                returnArr.add(task);
            } else if (task.getPointA().getId() == task.getPointB().getId()) {
                returnArr.add(task);
            } else {
                log.info("this task is duplicate. pointAId: {}, pointBId: {}", task.getPointA().getId(), task.getPointB().getId());
            }
        });
        return returnArr;
    }
}