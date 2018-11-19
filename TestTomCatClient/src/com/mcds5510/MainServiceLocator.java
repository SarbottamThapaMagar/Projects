/**
 * MainServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510;

public class MainServiceLocator extends org.apache.axis.client.Service implements com.mcds5510.MainService {

    public MainServiceLocator() {
    }


    public MainServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MainServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Main
    private java.lang.String Main_address = "http://localhost:8080/TestTomCat/services/Main";

    public java.lang.String getMainAddress() {
        return Main_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MainWSDDServiceName = "Main";

    public java.lang.String getMainWSDDServiceName() {
        return MainWSDDServiceName;
    }

    public void setMainWSDDServiceName(java.lang.String name) {
        MainWSDDServiceName = name;
    }

    public com.mcds5510.Main getMain() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Main_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMain(endpoint);
    }

    public com.mcds5510.Main getMain(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mcds5510.MainSoapBindingStub _stub = new com.mcds5510.MainSoapBindingStub(portAddress, this);
            _stub.setPortName(getMainWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMainEndpointAddress(java.lang.String address) {
        Main_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mcds5510.Main.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mcds5510.MainSoapBindingStub _stub = new com.mcds5510.MainSoapBindingStub(new java.net.URL(Main_address), this);
                _stub.setPortName(getMainWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Main".equals(inputPortName)) {
            return getMain();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mcds5510.com", "MainService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mcds5510.com", "Main"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Main".equals(portName)) {
            setMainEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
