package org.pdm.ib.converter.impl;

import org.pdm.ib.command.CustomerCommand;
import org.pdm.ib.converter.Converter;
import org.pdm.ib.model.UserProfile;

public final class CustomerConverter implements Converter<CustomerCommand, UserProfile> {

    @Override
    public UserProfile convertToEntity(CustomerCommand customerCommand) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(customerCommand.firstName);
        userProfile.setLastName(customerCommand.lastName);
        return userProfile;
    }

    @Override
    public CustomerCommand convertToCommand(UserProfile userProfile) {
        return new CustomerCommand.Builder()
                .withFirstName(userProfile.getFirstName())
                .withLastName(userProfile.getLastName())
                .build();
    }
}
