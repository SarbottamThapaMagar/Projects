package com.mcds5510;

public class MainProxy implements com.mcds5510.Main {
  private String _endpoint = null;
  private com.mcds5510.Main main = null;
  
  public MainProxy() {
    _initMainProxy();
  }
  
  public MainProxy(String endpoint) {
    _endpoint = endpoint;
    _initMainProxy();
  }
  
  private void _initMainProxy() {
    try {
      main = (new com.mcds5510.MainServiceLocator()).getMain();
      if (main != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)main)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)main)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (main != null)
      ((javax.xml.rpc.Stub)main)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mcds5510.Main getMain() {
    if (main == null)
      _initMainProxy();
    return main;
  }
  
  public void userTransactionSelection(java.lang.String transactionType) throws java.rmi.RemoteException{
    if (main == null)
      _initMainProxy();
    main.userTransactionSelection(transactionType);
  }
  
  
}