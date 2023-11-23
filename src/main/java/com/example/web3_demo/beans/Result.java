package com.example.web3_demo.beans;

import com.example.web3_demo.beans.RBean;
import com.example.web3_demo.beans.RowEJB;
import com.example.web3_demo.beans.XBean;
import com.example.web3_demo.beans.YBean;
import com.example.web3_demo.entities.Row;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
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
   public void addRowSVG(){
        rowEJB.addSvg();
   }
    public List<Row> getResults() {
        return rowEJB.getAllPoints();
    }
    public void deleteResults(){
        rowEJB.deleteAll();
    }
    public void sendAllPoints(){
        rowEJB.sendAllJson();
    }
}
