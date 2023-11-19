package com.example.web3_demo;

import jakarta.ejb.EJB;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
//beanOfElements
//ExampleCDI
//ResultControllerBean
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private XBean xBean;
    private YBean yBean;
    private RBean rBean;
    @EJB
    private RowEJB rowEJB;

    public void addRow(String x, String y, String r){
        rowEJB.add(x,y,r);
   }
    public List<Row> getResults() {
        return rowEJB.getAllPoints();
    }
    public void deleteResults(){
        rowEJB.deleteAll();
    }
}
