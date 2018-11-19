/**
 * MainService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510;

public interface MainService extends javax.xml.rpc.Service {
    public java.lang.String getMainAddress();

    public com.mcds5510.Main getMain() throws javax.xml.rpc.ServiceException;

    public com.mcds5510.Main getMain(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
