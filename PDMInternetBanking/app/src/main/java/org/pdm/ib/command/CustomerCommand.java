package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

import org.pdm.ib.model.enums.CustType;

import java.util.Date;
import java.util.List;

public class CustomerCommand {

    @SerializedName("id")
    public Long id;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("birthDate")
    public Date birthDate;
    @SerializedName("serialNumber")
    public String serialNumber;
    @SerializedName("custType")
    public CustType custType;
    @SerializedName("accountList")
    public List<AccountCommand> accountList;

    public CustomerCommand(Long id, String lastName, String firstName, Date birthDate, String serialNumber, CustType custType, List<AccountCommand> accountList) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.serialNumber = serialNumber;
        this.custType = custType;
        this.accountList = accountList;
    }
}
