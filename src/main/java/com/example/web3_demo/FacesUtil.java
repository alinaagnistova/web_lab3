package com.example.web3_demo;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class FacesUtil {

    public static void errorMessage(String componentId, String message, String detail) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail));
    }
}