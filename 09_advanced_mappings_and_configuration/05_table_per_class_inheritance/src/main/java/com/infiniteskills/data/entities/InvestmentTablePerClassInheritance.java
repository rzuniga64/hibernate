package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
// @Inheritance is a JPA annotation.  The TABLE_PER_CLASS strategy mean that we will have a table for each of our
// concrete classes. We will have a table for Bond and a table for Stock. This very similar to using the mapped
// superclass strategy but now we will be able to use the Investment type within associations and queries.
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class InvestmentTablePerClassInheritance {

    // TABLE_PER_CLASS strategy requires us to put a few other things in place within our abstract class.  We need to
    // specify our @Id within the abstract class Investment.  This will serve as the identifier for the bond and
    // stock entities.
    //
    // Disadvantage: Hibernate has to perform a union. When we have just two records that is not a big deal. But
    // if there were thousands of records the union operation is going to get expensive. So there is a price to
    // pay with table per class inheritance.
    @Id
    // We are going to use a different type of strategy to generate a value for this field. We are
    // going to use a key generator. We are going to specify the strategy as table.
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")
    // Then we need to create the table generator.  If you remember, within the database we have the ifinances_
    // key table so specify the information for that table from the database. We put a table generator in place
    // because we cannot use the standard identity generator that is used by MySQL. We need to use a table generator
    // when using the TABLE_PER_CLASS strategy.
    @TableGenerator(table="ifinances_keys", pkColumnName = "pk_name",
            valueColumnName = "pk_value", name="key_generator")
    // Finally, put the column name in place.
    @Column(name="INVESTMENT_ID")
    private Long investmentId;

    // Add portfolio field to this abstract class. We are going to make the association with this field bidirectional
    // because we need to provide the mapping information for the association within the abstract class. We always
    // have to specify our @JoinColumn annotation column here because the investment will actually be the owning entity.
    @JoinColumn(name="PORTFOLIO_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private PortfolioTablePerClassInheritance portfolio;

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

    public PortfolioTablePerClassInheritance getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioTablePerClassInheritance portfolio) {
        this.portfolio = portfolio;
    }
}