package com.poom.quest.services.model;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.abstractModel.Log;

@Entity
public class PaymentLog extends Log {

	private static final long serialVersionUID = 1L;
	
	private int relevantMoney;
	
	private int prevPoint;
	
	private int currentPoint;

	public int getRelevantMoney() {
		return relevantMoney;
	}

	public void setRelevantMoney(int relevantMoney) {
		this.relevantMoney = relevantMoney;
	}

	public int getPrevPoint() {
		return prevPoint;
	}

	public void setPrevPoint(int prevPoint) {
		this.prevPoint = prevPoint;
	}

	public int getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	
	
	
}
