/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rha.control;

import com.rha.entity.BookedResource;
import com.rha.entity.Division;
import com.rha.entity.Project;
import com.rha.entity.Step;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alacambra
 */
@Stateless
public class DataImport {

    @PersistenceContext
    EntityManager em;

    Division d;
    
    public void loadData() {
        
        d = new Division();
        d.setName("java");
        em.persist(d);

        try {
            Stream<String> lines = Files.lines(
                    Paths.get("/Users/albertlacambra1/git/rha/src/main/resources/", 
                            "servicedata.csv"));

            lines.map(l -> l.split(",")).forEach(this::insertRow);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    void insertRow(String... row) {
        
        String projectName = row[0];
        Project p = new Project();
        
        p.setAbscence(10);
        p.setName(projectName);
        p.setProbability(90);
        p.setStep(Step.MONTH);
        
        em.persist(p);

        for (int i = 0; i < row.length - 1 ; i++) {
             BookedResource br = new BookedResource();
             String v = "".equals(row[i+1]) ? "0" : row[i+1];
             br.setBooked(Math.round(Float.parseFloat(v)));
             br.setDivision(d);
             br.setProject(p);
             br.setPosition(i);
             em.persist(br);
        }
    }
}