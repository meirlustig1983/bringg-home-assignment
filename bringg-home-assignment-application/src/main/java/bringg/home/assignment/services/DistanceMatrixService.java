package bringg.home.assignment.services;

import bringg.home.assignment.converters.PointConverter;
import bringg.home.assignment.converters.PointDataConverter;
import bringg.home.assignment.dto.Point;
import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import bringg.home.assignment.filters.TasksFilter;
import bringg.home.assignment.nodes.Node;
import bringg.home.assignment.utils.NodesUtils;
import bringg.home.assignment.utils.QueuesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

@Slf4j
@Component
public class DistanceMatrixService {

    private PointConverter pointConverter;

    private PointDataConverter pointDataConverter;

    private List<TasksFilter> tasksFilters;

    private QueuesUtils queuesUtils;

    private NodesUtils nodesUtils;

    @Autowired
    public DistanceMatrixService(PointConverter pointConverter, PointDataConverter pointDataConverter,
                                 List<TasksFilter> tasksFilters, QueuesUtils queuesUtils,
                                 NodesUtils nodesUtils) {

        this.pointConverter = pointConverter;
        this.pointDataConverter = pointDataConverter;
        this.tasksFilters = tasksFilters;
        this.queuesUtils = queuesUtils;
        this.nodesUtils = nodesUtils;
    }

    public String[][] calculateDistanceMatrix(List<Point> points, int threadPoolSize) {

        if (threadPoolSize < 1) {
            throw new IllegalArgumentException("missing argument: threadPoolSize");
        }

        if (CollectionUtils.isEmpty(points)) {
            throw new IllegalArgumentException("missing argument: points");
        }


        String[][] distanceMatrix = new String[points.size()][points.size()];

        List<PointData> pointDataList = pointConverter.convertToPointDataList(points);
        List<Task> tasks = pointDataConverter.covertToTasksList(pointDataList);

        for (TasksFilter filter : tasksFilters) {
            tasks = filter.filter(tasks, distanceMatrix);
        }

        List<Queue<Task>> queues = queuesUtils.createQueues(threadPoolSize);
        List<Node> nodes = nodesUtils.createNodes(queues, threadPoolSize, distanceMatrix);
        queuesUtils.sendTasks(queues, tasks);
        nodes.forEach(Thread::start);

        if (nodesUtils.isFinished(nodes)) {
            log.info("calculate distance matrix process has been finished");
        }
        return distanceMatrix;
    }
}
