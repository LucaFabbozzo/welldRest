package com.LucaFabb.welldRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * La classe PointController è un controller REST che espone API per gestire i punti nel piano.
 * Fornisce endpoint per aggiungere, ottenere e rimuovere punti, nonché per ottenere linee che attraversano un numero minimo di punti.
 * È il punto di accesso per interagire con il servizio PointService e il servizio LineService.
 */

@RestController
@RequestMapping("/api")
public class PointController {
    @Autowired
    private PointService pointService;

    @Autowired
    private LineService lineService;

    @PostMapping("/point")
    public void addPoint(@RequestBody Point point) {
        pointService.addPoint(point);
    }

    @GetMapping("/space")
    public List<Point> getAllPoints() {
        return pointService.getAllPoints();
    }

    @GetMapping("/lines/{n}")
    public List<List<Point>> getLines(@PathVariable int n) {
        return lineService.findLines(n);
    }

    @DeleteMapping("/space")
    public void clearSpace() {
        pointService.clearPoints();
    }
}
