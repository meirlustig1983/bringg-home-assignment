package bringg.home.assignment.converters;

import bringg.home.assignment.dto.Point;
import bringg.home.assignment.dto.PointData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PointConverter {

    public List<PointData> convertToPointDataList(List<Point> points) {
        int id = 0;
        List<PointData> pointDataList = new ArrayList<>();
        for (Point point : points) {
            PointData pointData = new PointData().setId(id).setX(point.getX()).setY(point.getY());
            log.info("create point data. id: {}, x: {}, y: {}", pointData.getId(), pointData.getX(), pointData.getY());
            pointDataList.add(pointData);
            id++;
        }
        return pointDataList;
    }
}