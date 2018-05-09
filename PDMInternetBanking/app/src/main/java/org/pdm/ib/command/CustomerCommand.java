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

    private CustomerCommand(Builder builder) {
        id = builder.id;
        lastName = builder.lastName;
        firstName = builder.firstName;
        birthDate = builder.birthDate;
        serialNumber = builder.serialNumber;
        custType = builder.custType;
        accountList = builder.accountList;
    }


    public static final class Builder {
        private Long id;
        private String lastName;
        private String firstName;
        private Date birthDate;
        private String serialNumber;
        private CustType custType;
        private List<AccountCommand> accountList;

        public Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withBirthDate(Date val) {
            birthDate = val;
            return this;
        }

        public Builder withSerialNumber(String val) {
            serialNumber = val;
            return this;
        }

        public Builder withCustType(CustType val) {
            custType = val;
            return this;
        }

        public Builder withAccountList(List<AccountCommand> val) {
            accountList = val;
            return this;
        }

        public CustomerCommand build() {
            return new CustomerCommand(this);
        }
    }
}
