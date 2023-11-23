package com.example.web3_demo.beans;

import com.example.web3_demo.entities.Row;
import com.example.web3_demo.validation.Checkout;
import com.example.web3_demo.validation.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.primefaces.PrimeFaces;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Stateless
public class RowEJB {
    private final int MIN_X = -2;
    private final int MAX_X = 2;
    private final int MIN_Y = -3;
    private final int MAX_Y = 5;
    private final float[] R_VALUES = {1,2,3,4,5};
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public void add(String xStr, String yStr, String rStr) {
        try {
            float x = Float.parseFloat(xStr);
            float y = Float.parseFloat(yStr);
            float r = Float.parseFloat(rStr);
            long time = System.nanoTime();
            if (x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_Y && Validator.isRInRange(r, R_VALUES)) {
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
    public void addSvg() {
        Map<String, String> values = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            float x = Float.parseFloat(values.get("x"));
            float y = Float.parseFloat(values.get("y"));
            float r = Float.parseFloat(values.get("r"));
            long time = System.nanoTime();
            if (x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_Y && Validator.isRInRange(r, R_VALUES)) {
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
            System.out.println("---------->ERROR: SVG ADD");
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
    public void sendAllJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[]";
        try {
            json = objectMapper.writeValueAsString(getAllPoints());
            System.out.println(json);
            PrimeFaces.current().ajax().addCallbackParam("response", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            PrimeFaces.current().ajax().addCallbackParam("response", "[]");

        }
    }

}

