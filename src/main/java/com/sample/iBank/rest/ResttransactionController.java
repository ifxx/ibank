package com.sample.iBank.rest;

import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

public class ResttransactionController implements ModelDriven<Object> {
	
	private static final long serialVersionUID = 89268916175477691L;
	private Resttransaction model = new Resttransaction();
	private String id;
	private Collection<Resttransaction> list;

	public HttpHeaders create() {
		
		int num = TransactionService.updateAccount(model);
		
		//return new DefaultHttpHeaders("success").setLocationId(model.getUserid());
		HttpHeaders hd = new DefaultHttpHeaders("create");
		hd.setStatus(num);
		
		return hd;
	}
	public HttpHeaders createContinue() {
		
		int num = TransactionService.updateAccount(model);
		
		//return new DefaultHttpHeaders("success").setLocationId(model.getUserid());
		HttpHeaders hd = new DefaultHttpHeaders("create");
		hd.setStatus(num);
		
		return hd;
	}

	public HttpHeaders destroy() {
		return new DefaultHttpHeaders("destroy");
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show").disableCaching();
	}

	public HttpHeaders update() {
		//MessageService.save(model);
		return new DefaultHttpHeaders("update");
	}

	public HttpHeaders index() {
		//list = MessageService.findAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public Object getModel() {
		return (list != null ? list : model);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.model = TransactionService.getTransactionDetails(id);
		}
		this.id = id;
	}

}
