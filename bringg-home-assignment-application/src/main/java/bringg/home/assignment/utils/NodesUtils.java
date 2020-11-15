package bringg.home.assignment.utils;

import bringg.home.assignment.dto.Task;
import bringg.home.assignment.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Component
public class NodesUtils {

    private MathUtils mathUtils;

    @Autowired
    public NodesUtils(MathUtils mathUtils) {
        this.mathUtils = mathUtils;
    }

    public List<Node> createNodes(List<Queue<Task>> queues, int size, String[][] distanceMatrix) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node node = new Node(Integer.toString(i), queues.get(i), mathUtils, distanceMatrix);
            nodes.add(node);
        }
        return nodes;
    }

    public boolean isFinished(List<Node> nodes) {
        boolean isNotFinished = true;
        while (isNotFinished) {
            boolean atLeastOneThreadIsAlive = false;
            for (Node node : nodes) {
                atLeastOneThreadIsAlive = atLeastOneThreadIsAlive | node.isAlive();
            }
            isNotFinished = atLeastOneThreadIsAlive;
        }
        return true;
    }
}