package org.pdm.ib.model;

import org.pdm.ib.model.enums.CustType;

import java.util.Date;
import java.util.List;

public class Customer {

    private Long id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String serialNumber;
    private CustType custType;
    private List<Account> accountList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CustType getCustType() {
        return custType;
    }

    public void setCustType(CustType custType) {
        this.custType = custType;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
