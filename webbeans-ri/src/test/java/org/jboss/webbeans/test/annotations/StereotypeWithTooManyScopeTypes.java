package org.jboss.webbeans.test.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.webbeans.ApplicationScoped;
import javax.webbeans.RequestScoped;
import javax.webbeans.Stereotype;

@Stereotype
@Target( { TYPE })
@Retention(RUNTIME)
@ApplicationScoped
@RequestScoped
public @interface StereotypeWithTooManyScopeTypes
{

}
