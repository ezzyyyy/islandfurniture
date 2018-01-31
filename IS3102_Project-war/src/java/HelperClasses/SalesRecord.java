/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

/**
 *
 * @author caeden
 */
public class SalesRecord {
    
     private long memberID;
     private double amountPaid;
     private long countryID;
     private long storeID;

    public SalesRecord() {
    }
     

    public SalesRecord(long memberID, double amountPaid, long countryID, long storeID) {
        this.memberID = memberID;
        this.amountPaid = amountPaid;
        this.countryID = countryID;
        this.storeID = storeID;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public long getCountryID() {
        return countryID;
    }

    public void setCountryID(long countryID) {
        this.countryID = countryID;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }
    
     
}
