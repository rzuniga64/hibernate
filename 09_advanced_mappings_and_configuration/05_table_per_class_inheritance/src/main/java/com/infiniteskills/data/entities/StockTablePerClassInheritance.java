package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "STOCK")
public class StockTablePerClassInheritance extends InvestmentTablePerClassInheritance {

	@Column(name = "SHARE_PRICE")
	private BigDecimal sharePrice;

	@Column(name = "QUANTITY")
	private BigDecimal quantity;

	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}

