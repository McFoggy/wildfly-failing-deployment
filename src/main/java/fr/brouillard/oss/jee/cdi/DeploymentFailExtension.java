package fr.brouillard.oss.jee.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.DeploymentException;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class DeploymentFailExtension implements Extension {
    public <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat, BeanManager beanManager) {
        if ("fr.brouillard.oss.jee.control.FailIfPresent".equals(pat.getAnnotatedType().getJavaClass().getName())) {
            throw new DeploymentException("unexpected state detected, aborting deployment");
        }
    }
}
