package com.infiniteskills.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PORTFOLIO")
public class PortfolioTablePerClassInheritance {

	@Id
	@GeneratedValue
	@Column(name="PORTFOLIO_ID")
	private Long portfolioId;

	@Column(name="NAME")
	private String name;

    // We want a collection value type on a different entity. So here we have a portfolio entity and its going to
    // hold a bunch of investments. We would like to store those investments in a List or some other Collection. We
    // are not able to do that with a mapped superclass. So we have to put in a different strategy that allows us to
    // build entity associations and allow us to query across a class hierarchy.

	// Establish this side of the association with @OneToMany. Mapped by portfolio field in investment abstract class.
    // That is the field that needs to provide all of our mapping information. We can't put a join column on this
    // investment field in this portfolio entity.  It just won't work like that because of inheritance. Now need
    // to make two changes in stock and bond.
	@OneToMany(mappedBy="portfolio")
	private List<InvestmentTablePerClassInheritance> investments = new ArrayList<InvestmentTablePerClassInheritance>();

	public Long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

    public List<InvestmentTablePerClassInheritance> getInvestments() {
        return investments;
    }

    public void setInvestments(List<InvestmentTablePerClassInheritance> investments) {
        this.investments = investments;
    }
}
