package com.LucaFabb.welldRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe LineService aiuta a trovare le linee
 * che passano attraverso almeno un certo numero di punti nello spazio.
 * Per fare questo, prende tutti i punti nello spazio e calcola la pendenza delle linee che
 * collegano tutte le possibili coppie di punti. Dopodiché, raggruppa i punti che appartengono
 * alla stessa linea utilizzando la pendenza e l'intercetta. Infine, seleziona le
 * linee che hanno almeno il numero desiderato di punti e le restituisce.
 * la classe cerca di trovare le linee che attraversano molti punti nello spazio.
 */



@Service
public class LineService {
    @Autowired
    private PointService pointService;

    public List<List<Point>> findLines(int n) {
        List<List<Point>> lines = new ArrayList<>();
        Map<Double, Map<Double, List<Point>>> slopes = new HashMap<>();

        // Ottieni tutti i punti nello spazio.
        List<Point> allPoints = pointService.getAllPoints();

        // Calcola tutte le combinazioni possibili di coppie di punti.
        for (int i = 0; i < allPoints.size(); i++) {
            for (int j = i + 1; j < allPoints.size(); j++) {
                // Calcola la pendenza della linea che passa attraverso la coppia di punti.
                double slope = calculateSlope(allPoints.get(i), allPoints.get(j));

                // Raggruppa i punti con la stessa pendenza.
                slopes.putIfAbsent(slope, new HashMap<>());
                // Usa l'intercetta per determinare univocamente la linea.
                slopes.get(slope).putIfAbsent(calculateIntercept(allPoints.get(i), slope), new ArrayList<>());
                // Aggiungi i punti alla linea corrispondente.
                slopes.get(slope).get(calculateIntercept(allPoints.get(i), slope)).add(allPoints.get(i));
                slopes.get(slope).get(calculateIntercept(allPoints.get(i), slope)).add(allPoints.get(j));
            }
        }

        // Seleziona le linee con almeno N punti e aggiungile all'elenco risultante.
        for (Map.Entry<Double, Map<Double, List<Point>>> entry : slopes.entrySet()) {
            for (Map.Entry<Double, List<Point>> subEntry : entry.getValue().entrySet()) {
                if (subEntry.getValue().size() >= n) {
                    lines.add(subEntry.getValue());
                }
            }
        }

        // Restituisci l'elenco delle linee.
        return lines;
    }

    // Calcola la pendenza della linea che passa attraverso due punti.
    private double calculateSlope(Point p1, Point p2) {
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    // Calcola l'intercetta della linea usando un punto e la sua pendenza.
    private double calculateIntercept(Point p, double slope) {
        return p.getY() - slope * p.getX();
    }
}
