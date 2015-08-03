package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BOND")
public class BondMappedSuperclass extends InvestmentMappedSuperclass{

	@Id
	@GeneratedValue
	@Column(name = "BOND_ID")
	private Long bondId;

	@Column(name = "VALUE")
	private BigDecimal value;

	@Column(name = "INTEREST_RATE")
	private BigDecimal interestRate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "MATURITY_DATE")
	private Date maturityDate;

	public Long getBondId() {
		return bondId;
	}

	public void setBondId(Long bondId) {
		this.bondId = bondId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
}
