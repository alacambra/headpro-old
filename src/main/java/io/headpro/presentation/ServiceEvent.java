/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.headpro.presentation;

import io.headpro.entity.Service;
import io.headpro.presentation.util.JsfUtil;

/**
 *
 * @author alacambra
 */
public class ServiceEvent {
 
    Service service;
    JsfUtil.PersistAction action;

    public ServiceEvent(Service service, JsfUtil.PersistAction action) {
        this.service = service;
        this.action = action;
    }

    public Service getService() {
        return service;
    }
}
