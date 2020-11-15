package bringg.home.assignment.converters;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PointDataConverter {

    public List<Task> covertToTasksList(List<PointData> points) {
        List<Task> tasks = new ArrayList<>();
        for (PointData pointA : points) {
            for (PointData pointB : points) {
                Task task = new Task().setPointA(pointA).setPointB(pointB);
                log.info("create task. pointAId: {}, pointBId: {}", pointA.getId(), pointB.getId());
                tasks.add(task);
            }
        }
        return tasks;
    }
}