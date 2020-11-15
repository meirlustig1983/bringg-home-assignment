package bringg.home.assignment.nodes;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import bringg.home.assignment.utils.MathUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
public class Node extends Thread {

    private final String nodeId;

    private final Queue<Task> myQueue;

    private MathUtils mathUtils;

    private String[][] distanceMatrix;

    public Node(String nodeId, Queue<Task> myQueue, MathUtils mathUtils, String[][] distanceMatrix) {
        this.nodeId = nodeId;
        this.myQueue = myQueue;
        this.mathUtils = mathUtils;
        this.distanceMatrix = distanceMatrix;
    }

    @SneakyThrows
    public void run() {
        log.info("start a new node, nodeId: {}", nodeId);
        Task task = myQueue.peek();

        while (task != null) {

            log.info("new task has been received, nodeId: {}, pointAId: {}, pointBId: {}", nodeId, task.getPointA().getId(), task.getPointB().getId());
            PointData pointDataA = task.getPointA();
            PointData pointDataB = task.getPointB();

            double distance = mathUtils.calcDistance(pointDataA, pointDataB);
            log.info("the distance between two 2D points has been calculated. nodeId: {}, pointA: ({},{}), pointB: ({},{}), distance: {}",
                nodeId, task.getPointA().getX(), task.getPointA().getY(), task.getPointB().getX(), task.getPointB().getY(), distance);

            distanceMatrix[pointDataA.getId()][pointDataB.getId()] = String.format("%.2f", distance);
            log.info("save data to map. nodeId: {}, matrixPoint: ({},{}), data: {}", nodeId, pointDataA.getId(), pointDataB.getId(), distance);
            distanceMatrix[pointDataB.getId()][pointDataA.getId()] = String.format("%.2f", distance);
            log.info("save data to map. nodeId: {}, matrixPoint: ({},{}), data: {}", nodeId, pointDataB.getId(), pointDataA.getId(), distance);

            myQueue.remove();
            task = myQueue.peek();
        }
        log.info("node terminated, nodeId: {}", nodeId);
    }
}