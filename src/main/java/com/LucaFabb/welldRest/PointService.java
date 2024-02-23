package com.LucaFabb.welldRest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *La classe PointService gestisce l'aggiunta, l'ottenimento e la rimozione dei punti nel piano.
 */

@Service
public class PointService {
    private List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getAllPoints() {
        return points;
    }

    public void clearPoints() {
        points.clear();
    }
}
