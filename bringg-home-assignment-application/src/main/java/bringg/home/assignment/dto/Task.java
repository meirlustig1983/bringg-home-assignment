package bringg.home.assignment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Task {

    private PointData pointA;

    private PointData pointB;
}