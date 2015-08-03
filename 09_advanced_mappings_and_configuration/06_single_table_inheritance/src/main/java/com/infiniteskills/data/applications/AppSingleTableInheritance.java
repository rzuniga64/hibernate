package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.BondSingleTableInheritance;
import com.infiniteskills.data.entities.InvestmentSingleTableInheritance;
import com.infiniteskills.data.entities.PortfolioSingleTableInheritance;
import com.infiniteskills.data.entities.StockSingleTableInheritance;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

public class AppSingleTableInheritance {

	public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try{
            // Create an SessionFactory
            sessionFactory = HibernateUtil.getSessionFactory();
            // Create a session, open Persistence Context 1
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            PortfolioSingleTableInheritance portfolio = new PortfolioSingleTableInheritance();
            portfolio.setName("First Investments");

            // create transient stock entity
            StockSingleTableInheritance stock = createStock();
            // set portfolio on stock entity
            stock.setPortfolio(portfolio);

            // create transient bond entity
            BondSingleTableInheritance bond = createBond();
            // set portfolio on bond entity
            bond.setPortfolio(portfolio);

            // now we manage both sides of the relationship by adding the stock and bond to the collection of
            // investments on the portfolio.
            portfolio.getInvestments().add(stock);
            portfolio.getInvestments().add(bond);

            // make stock and bond entity persistent, we will allow them to persist the portfolio through the cascade.
            session.save(stock);
            session.save(bond);

            // Persist the entities to the database
            tx.commit();

            PortfolioSingleTableInheritance dbPortfolio = (PortfolioSingleTableInheritance) session.
                    get(PortfolioSingleTableInheritance.class, portfolio.getPortfolioId());
            session.refresh(dbPortfolio);

            for (InvestmentSingleTableInheritance investment:dbPortfolio.getInvestments())
                System.out.println(investment.getName());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            // Close Persistent Context 1
            session.close();
            sessionFactory.close();
        }
	}

    private static BondSingleTableInheritance createBond() {
        BondSingleTableInheritance bond = new BondSingleTableInheritance();
        bond.setInterestRate(new BigDecimal("123.22"));
        bond.setIssuer("JP Morgan Chase");
        bond.setMaturityDate(new Date());
        bond.setPurchaseDate(new Date());
        bond.setName("Long Term Bond Purchases");
        bond.setValue(new BigDecimal("10.22"));
        return bond;
    }

    private static StockSingleTableInheritance createStock(){
        StockSingleTableInheritance stock = new StockSingleTableInheritance();
        stock.setIssuer("Allen Edmonds");
        stock.setName("Private American Stock Purchases");
        stock.setPurchaseDate(new Date());
        stock.setQuantity(new BigDecimal("1922"));
        stock.setSharePrice(new BigDecimal("100.00"));
        return stock;
    }
}
