package net.yumix.simplemail.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jakarta.mail.Authenticator;
import jakarta.mail.MailSessionDefinition;
import jakarta.mail.MailSessionDefinitions;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class SessionLoader {

    private Map<String, Session> sessions;
    
    public SessionLoader() {
        this.sessions = new HashMap<>();
    }

    public void load(Class<?> clazz) {
        var definitions = clazz.getAnnotation(MailSessionDefinitions.class);
        if (definitions != null) {
            for (var definition : definitions.value()) {
                String name = definition.name();
                Session session = createSession(definition);
                sessions.put(name, session);
            }
        }
        
        var definition = clazz.getAnnotation(MailSessionDefinition.class);
        if (definition != null) {
            String name = definition.name();
            Session session = createSession(definition);
            sessions.put(name, session);
        }
    }
    
    public Set<String> names() {
        return sessions.keySet();
    }
    
    public boolean containsName(String name) {
        return sessions.containsKey(name);
    }
    
    public Session get(String name) {
        return sessions.get(name);
    }
    
    public Session createSession(MailSessionDefinition definition) {
        var storeProtocol = definition.storeProtocol();
        var transportProtocol = definition.transportProtocol();
        var host = definition.host();
        var user = definition.user();
        var password = definition.password();
        var from = definition.from();
        var properties = definition.properties();
        
        var props = new Properties();
        props.setProperty("mail.store.protocol", storeProtocol);
        props.setProperty("mail.transport.protocol", transportProtocol);
        props.setProperty("mail.host", host);
        props.setProperty("mail.user", user);
        props.setProperty("mail.from", from);
        
        for (var property : properties) {
            var key = property.substring(0, property.indexOf("="));
            var value = property.substring(property.indexOf("=") + 1);
            props.setProperty(key, value);
        }
        
        if (user.isEmpty()) {
            return Session.getInstance(props);
        } else {
            return Session.getInstance(props, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
        }
    }
}
