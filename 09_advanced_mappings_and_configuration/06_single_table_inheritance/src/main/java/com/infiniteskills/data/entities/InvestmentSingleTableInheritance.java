package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
// @Inheritance is a JPA annotation.  The SINGLE_TABLE strategy means that we will take every field in our entity
// and associate with a column upon a single table. What we have done is taken all of the fields within our stock
// and our bond entities and we have placed them in the investment table. We see the common and distinct fields
// within the two entities. What will happen as we populate this table some of the fields will just wind up being
// null. When we insert a bond into the database we will not be specifying a share price. When we insert a stock
// we won't be specifying the interest rate on the stock. So its a trade. Sometimes we'll just have to accept there
// be some null values within this table. Some DBAs may not like that but they may not like the alternative of a
// TABLE_PER_CLASS strategy where we are going to duplicate a lot of fields. Its always a tradeoff when mapping
// inheritance.
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn is a column that will reside within the inheritance table that will be used to discern the
// the type of investment we are working with. So there be a value put in that column for stock and one for bond.
@DiscriminatorColumn(name="INVESTMENT_TYPE")
@Table(name="INVESTMENT")
public abstract class InvestmentSingleTableInheritance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="INVESTMENT_ID")
    private Long investmentId;

    // Add portfolio field to this abstract class. We are going to make the association with this field bidirectional
    // because we need to provide the mapping information for the association within the abstract class. We always
    // have to specify our @JoinColumn annotation column here because the investment will actually be the owning entity.
    @JoinColumn(name="PORTFOLIO_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private PortfolioSingleTableInheritance portfolio;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ISSUER")
    protected String issuer;

    @Column(name = "PURCHASE_DATE")
    protected Date purchaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getInvestmentId() { return investmentId; }

    public void setInvestmentId(Long investmentId) {
        this.investmentId = investmentId;
    }

    public PortfolioSingleTableInheritance getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioSingleTableInheritance portfolio) {
        this.portfolio = portfolio;
    }
}