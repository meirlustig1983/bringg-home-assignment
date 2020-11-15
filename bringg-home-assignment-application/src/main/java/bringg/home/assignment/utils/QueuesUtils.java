package bringg.home.assignment.utils;

import bringg.home.assignment.dto.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class QueuesUtils {

    public List<Queue<Task>> createQueues(int size) {
        List<Queue<Task>> queueList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Queue<Task> queue = new LinkedList<>();
            queueList.add(queue);
        }
        return queueList;
    }

    public void sendTasks(List<Queue<Task>> queues, List<Task> tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            queues.get(calcNextQueueIndex(i, queues.size())).add(tasks.get(i));
        }
    }

    private int calcNextQueueIndex(int i, int size) {
        if (i < size) {
            return i;
        } else {
            return i % size;
        }
    }
}