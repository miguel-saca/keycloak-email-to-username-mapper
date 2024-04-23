package com.ssma;

import org.keycloak.broker.provider.AbstractIdentityProviderMapper;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.saml.SAMLIdentityProviderFactory;
import org.keycloak.models.IdentityProviderMapperModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

public class EmailToUsernameMapper extends AbstractIdentityProviderMapper {

    public static final String PROVIDER_ID = "email-to-username-mapper";
    private static final Logger log = Logger.getLogger(EmailToUsernameMapper.class);

    @Override
    public String[] getCompatibleProviders() {
        return new String[]{SAMLIdentityProviderFactory.PROVIDER_ID};
    }

    @Override
    public String getDisplayCategory() {
        return "Token Mapper";
    }

    @Override
    public String getDisplayType() {
        return "Convert Format Email Into Username";
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public void preprocessFederatedIdentity(KeycloakSession session, RealmModel realm, IdentityProviderMapperModel mapperModel, BrokeredIdentityContext context) {
        String email = context.getUsername();
        if (email == null || !email.contains("@")) {
            email = context.getId();
        }
        if (email != null && email.contains("@")) {
            String username = email.replace("@", "__");
            log.infov("Modifying username from email {0} to {1}", email, username);
            context.setUserAttribute(UserModel.USERNAME, username);
        } else {
            log.warn("Email was null, not modifying username");
        }
    }


    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return new ArrayList<>();
    }

    @Override
    public String getHelpText() {
        return "Converts the email address to a username by replacing '@' with '__'";
    }
}
