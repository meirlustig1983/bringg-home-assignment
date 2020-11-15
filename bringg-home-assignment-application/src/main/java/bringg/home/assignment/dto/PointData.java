package bringg.home.assignment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PointData {

    private int id;

    private double x;

    private double y;
}