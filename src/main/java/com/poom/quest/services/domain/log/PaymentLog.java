package com.poom.quest.services.domain.log;

import javax.persistence.Entity;

import com.poom.quest.services.domain.abstractModel.Domain;
import com.poom.quest.services.domain.abstractModel.Log;

@Entity
public class PaymentLog extends Log {

	private static final long serialVersionUID = 1L;
	
	private Integer money;
	
	private Integer point;

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
}
