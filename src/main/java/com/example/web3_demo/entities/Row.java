package com.example.web3_demo.entities;



import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Row implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private float x;
    @Column(nullable = false)
    private float y;
    @Column(nullable = false)
    private float r;
    @Column(nullable = false)
    private boolean result;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String scriptTime;

    public Row() {
    }

    public Row(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Row(Long id, float x, float y, float r, boolean result, String time, String scriptTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.time = time;
        this.scriptTime = scriptTime;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(String scriptTime) {
        this.scriptTime = scriptTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;

        Row row = (Row) o;

        if (Float.compare(getX(), row.getX()) != 0) return false;
        if (Float.compare(getY(), row.getY()) != 0) return false;
        if (Float.compare(getR(), row.getR()) != 0) return false;
        if (getResult() != row.getResult()) return false;
        if (!Objects.equals(id, row.id)) return false;
        if (getTime() != null ? !getTime().equals(row.getTime()) : row.getTime() != null) return false;
        return getScriptTime() != null ? getScriptTime().equals(row.getScriptTime()) : row.getScriptTime() == null;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (getX() != 0.0f ? Float.floatToIntBits(getX()) : 0);
        result1 = 31 * result1 + (getY() != 0.0f ? Float.floatToIntBits(getY()) : 0);
        result1 = 31 * result1 + (getR() != 0.0f ? Float.floatToIntBits(getR()) : 0);
        result1 = 31 * result1 + (getResult() ? 1 : 0);
        result1 = 31 * result1 + (getTime() != null ? getTime().hashCode() : 0);
        result1 = 31 * result1 + (getScriptTime() != null ? getScriptTime().hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", time='" + time + '\'' +
                ", scriptTime='" + scriptTime + '\'' +
                '}';
    }
}

