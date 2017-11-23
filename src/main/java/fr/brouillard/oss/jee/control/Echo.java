package fr.brouillard.oss.jee.control;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Echo {
    public String echo(String message) {
        return message;
    }
}
