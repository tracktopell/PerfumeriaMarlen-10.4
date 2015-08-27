/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.jsf.model;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.model.quickviews.ProductoQuickView;
import com.pmarlen.jsf.ProductoLazyMB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
/**
 *
 * @author alfredo
 */
public class ProductoQuickViewLazyDM extends LazyDataModel<ProductoQuickView> {
	private List<ProductoQuickView> datasource;
	private int totalPage;
    private transient final Logger logger = Logger.getLogger(ProductoQuickViewLazyDM.class.getSimpleName());
	
    public ProductoQuickViewLazyDM() {
        datasource = new ArrayList<ProductoQuickView>();
    }
     
    @Override
    public ProductoQuickView getRowData(String rowKey) {
		logger.info("-> getRowData:"+rowKey);
        for(ProductoQuickView car : datasource) {
            if(car.getCodigoBarras().equals(rowKey))
                return car;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(ProductoQuickView car) {
		logger.info("-> getRowKey:"+car);
        return car.getCodigoBarras();
    }
 
    @Override
    public List<ProductoQuickView> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		try {
			logger.info("-> load("+first+","+pageSize+","+sortField+","+sortOrder+","+filters+")");
			totalPage  =  ProductoDAO.getInstance().findAllLazyCount();
			datasource =  ProductoDAO.getInstance().findAllLazy(first, pageSize, sortField, pageSize, filters);
			logger.info("-> datasource.size = "+datasource.size());
		}catch(DAOException de){
			
		}

		return datasource;		
    }	

	public int getTotalCount() {
		try {
			totalPage  =  ProductoDAO.getInstance().findAllLazyCount();		
		}catch(DAOException de){
			
		}

		
		return totalPage;
	}
}
