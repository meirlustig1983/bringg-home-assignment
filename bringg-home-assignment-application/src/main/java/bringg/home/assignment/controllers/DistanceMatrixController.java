package bringg.home.assignment.controllers;

import bringg.home.assignment.converters.DataRequestConverter;
import bringg.home.assignment.services.DistanceMatrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/")
public class DistanceMatrixController {

    @Autowired
    private DataRequestConverter dataRequestConverter;

    @Autowired
    private DistanceMatrixService service;

    @GetMapping("help")
    public String help() {
        log.info("health checking request");
        return "Server is up and running.<br>"
            + "* health - checking service health.<br>"
            + "* calculate?points=1,2,-1,4&threads=2 - calculate two point distance matrix with 2 threads<br>"
            + "";
    }

    @GetMapping("health")
    public String health() {
        log.info("health check request");
        return "OK";
    }

    @GetMapping("calculate")
    public String calculate(String points, String threads) {
        log.info("calculate request. points: {}, threads: {}", points, threads);

        return printResponse(service.calculateDistanceMatrix(dataRequestConverter.convertPoints(points),
            dataRequestConverter.convertThreads(threads)));
    }

    private String printResponse(String[][] matrix) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < matrix.length; i++) {
            String[] arr = matrix[i];
            for (int j = 0; j < arr.length; j++) {
                sb.append("[").append(arr[j]).append("]");
            }
            sb.append("<br>");
        }
        return sb.toString();
    }
}