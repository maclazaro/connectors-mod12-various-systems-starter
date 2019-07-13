package com.mulesoft.training.model;

import java.sql.Timestamp;

public class Giftcard implements java.io.Serializable {

	private static final long serialVersionUID = 6626405262567394096L;
   
    private String number;
    private String sourceID;
    private Float balance;
    private Timestamp createdOn;
   
    public Giftcard() {}

    public Giftcard(String number, String sourceID, Float balance, Timestamp createdOn) {
        super();
        this.number = number;
        this.sourceID = sourceID;
        this.balance = balance;
        this.createdOn = createdOn;
    }

	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public String getSourceID() {
		return sourceID;
	}
	
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	
	public Float getBalance() {
		return balance;
	}
	
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
   
};
