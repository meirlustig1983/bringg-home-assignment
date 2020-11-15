package bringg.home.assignment.utils;

import bringg.home.assignment.dto.Task;
import bringg.home.assignment.nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NodesUtilsTest {

    private final QueuesUtils queuesUtils = new QueuesUtils();

    private final MathUtils mathUtils = new MathUtils();

    private final NodesUtils nodesUtils = new NodesUtils(mathUtils);

    @Test
    public void createNodes() {
        String[][] distanceMatrix = new String[3][3];
        int size = 3;
        List<Queue<Task>> queues = queuesUtils.createQueues(size);

        List<Node> result = nodesUtils.createNodes(queues, size, distanceMatrix);

        assertNotNull(result);
        assertEquals(result.size(), 3);
        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertNotNull(result.get(2));

        assertThat(result.get(0), instanceOf(Node.class));
        assertThat(result.get(1), instanceOf(Node.class));
        assertThat(result.get(2), instanceOf(Node.class));
    }
}