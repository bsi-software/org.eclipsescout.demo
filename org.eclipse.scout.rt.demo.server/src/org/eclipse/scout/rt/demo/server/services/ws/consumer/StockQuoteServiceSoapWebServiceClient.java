package org.eclipse.scout.rt.demo.server.services.ws.consumer;

import org.eclipse.scout.jaxws.annotation.ScoutWebServiceClient;
import org.eclipse.scout.jaxws.service.AbstractWebServiceClient;
import org.eclipse.scout.service.IService2;

import com.nexus6studio.services.StockQuoteService;
import com.nexus6studio.services.StockQuoteServiceSoap;

@ScoutWebServiceClient
public class StockQuoteServiceSoapWebServiceClient extends AbstractWebServiceClient<StockQuoteService, StockQuoteServiceSoap> implements IService2 {

  @Override
  protected String getConfiguredUrl() {
    return "http://services.nexus6studio.com/StockQuoteService.asmx";
  }
}
