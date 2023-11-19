package com.example.web3_demo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class XBean implements Serializable {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void validateX(FacesContext facesContext, UIComponent uiComponent, Object object){
        if (object == null){
            FacesMessage message = new FacesMessage("Укажите X");
            throw new ValidatorException(message);
        }
        String strObj = object.toString().trim();
        if (!strObj.matches("-?\\d+(\\.\\d+)?")) {
//            FacesUtil.errorMessage("valForm:x", "Ошибка","Нет, так не надо. Надо вот так: X - число");
            FacesMessage message = new FacesMessage("Нет, так не надо. Надо вот так: X - число");
            throw new ValidatorException(message);
        }
        float x = Float.parseFloat(strObj);
        if (x < -2 || x > 2){
            FacesMessage message = new FacesMessage("X должен быть в диапазоне [-2;2]");
            throw new ValidatorException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof XBean)) return false;

        XBean xBean = (XBean) o;

        return getValue() != null ? getValue().equals(xBean.getValue()) : xBean.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }
}
