package com.example.web3_demo;

import com.example.web3_demo.validation.Checkout;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
public class RowEJB {
    private final int MIN_X = -2;
    private final int MAX_X = 2;
    private final int MIN_Y = -3;
    private final int MAX_YR = 5;
    private final int MIN_R = 1;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public void add(String xStr, String yStr, String rStr) {
        try {
            float x = Float.parseFloat(xStr);
            float y = Float.parseFloat(yStr);
            float r = Float.parseFloat(rStr);
            System.out.println(x);
            System.out.println(y);
            System.out.println(r);
            long time = System.nanoTime();
            if (x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_YR && r >= MIN_R && r <= MAX_YR) {
                boolean result = Checkout.isHit(x, y, r);
                LocalTime currentTime = LocalTime.now();
                String curTime = currentTime.format(formatter);
                String scriptTime = String.format("%.2f", (double) (System.nanoTime() - time) * 0.0001);
                Row row = new Row(x, y, r);
                row.setResult(result);
                row.setTime(curTime);
                row.setScriptTime(scriptTime);
                entityManager.persist(row);

            }
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage("Неккоректные данные аргументов :(");
            throw new ValidatorException(message);
        }
    }

    public List<Row> getAllPoints() {
        Query query = entityManager.createQuery("select point from Row point");
        return query.getResultList();
    }
    public void deleteAll() {
        Query query = entityManager.createQuery("DELETE FROM Row");
        query.executeUpdate();
    }

}

