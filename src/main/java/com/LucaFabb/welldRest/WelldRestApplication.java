package com.LucaFabb.welldRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Questo progetto utilizza Spring Boot per gestire un set di
 * punti nel piano e determinare le linee che attraversano almeno
 * un certo numero di punti. Fornisce un'API REST per aggiungere,
 * ottenere e rimuovere punti, nonché per
 * ottenere le linee che attraversano un numero minimo di punti.
 * Una soluzione pratica per esplorare concetti fondamentali di
 * gestione dei dati geometrici con Java e Spring Boot.
 * */

@SpringBootApplication
public class WelldRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelldRestApplication.class, args);
	}
}
