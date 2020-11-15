package bringg.home.assignment.utils;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueuesUtilsTest {

    private final QueuesUtils queuesUtils = new QueuesUtils();

    @Test
    public void createQueues() {

        List<Queue<Task>> result = queuesUtils.createQueues(3);

        assertNotNull(result);
        assertEquals(result.size(), 3);
        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertNotNull(result.get(2));

        assertThat(result.get(0), instanceOf(LinkedList.class));
        assertThat(result.get(1), instanceOf(LinkedList.class));
        assertThat(result.get(2), instanceOf(LinkedList.class));
    }

    @Test
    public void sendTasks() {

        PointData pointDataA = new PointData().setId(0).setX(2).setY(-1);
        PointData pointDataB = new PointData().setId(1).setX(-2).setY(2);
        PointData pointDataC = new PointData().setId(2).setX(-1).setY(1);

        Task task1 = new Task().setPointA(pointDataA).setPointB(pointDataA);
        Task task2 = new Task().setPointA(pointDataA).setPointB(pointDataB);
        Task task3 = new Task().setPointA(pointDataA).setPointB(pointDataC);
        Task task4 = new Task().setPointA(pointDataB).setPointB(pointDataA);
        Task task5 = new Task().setPointA(pointDataB).setPointB(pointDataB);

        List<Task> tasks = Arrays.asList(task1, task2, task3, task4, task5);
        List<Queue<Task>> queues = queuesUtils.createQueues(3);

        assertNotNull(queues);
        assertEquals(queues.size(), 3);
        assertNotNull(queues.get(0));
        assertNotNull(queues.get(1));
        assertNotNull(queues.get(2));

        assertThat(queues.get(0), instanceOf(LinkedList.class));
        assertThat(queues.get(1), instanceOf(LinkedList.class));
        assertThat(queues.get(2), instanceOf(LinkedList.class));

        queuesUtils.sendTasks(queues,tasks);

        assertThat(queues.get(0).size(), equalTo(2));
        assertThat(queues.get(1).size(), equalTo(2));
        assertThat(queues.get(2).size(), equalTo(1));

        assertThat(queues.get(0).remove(), equalTo(task1));
        assertThat(queues.get(0).remove(), equalTo(task4));
        assertThat(queues.get(1).remove(), equalTo(task2));
        assertThat(queues.get(1).remove(), equalTo(task5));
        assertThat(queues.get(2).remove(), equalTo(task3));
    }
}
