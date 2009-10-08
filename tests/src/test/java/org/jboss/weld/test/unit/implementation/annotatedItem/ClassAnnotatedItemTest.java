package org.jboss.weld.test.unit.implementation.annotatedItem;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

import javax.enterprise.inject.stereotype.Stereotype;
import javax.inject.Qualifier;

import org.jboss.testharness.impl.packaging.Artifact;
import org.jboss.weld.introspector.WBClass;
import org.jboss.weld.introspector.jlr.WBClassImpl;
import org.jboss.weld.metadata.TypeStore;
import org.jboss.weld.resources.ClassTransformer;
import org.jboss.weld.test.AbstractWebBeansTest;
import org.testng.annotations.Test;

@Artifact
public class ClassAnnotatedItemTest extends AbstractWebBeansTest
{
	
   private final ClassTransformer transformer = new ClassTransformer(new TypeStore());
   
   @Test
   public void testDeclaredAnnotations()
   {
      WBClass<Order> annotatedElement = WBClassImpl.of(Order.class, transformer);
      assert annotatedElement.getAnnotations().size() == 1;
      assert annotatedElement.getAnnotation(Random.class) != null;
      assert annotatedElement.getJavaClass().equals(Order.class);
   }
   
   @Test
   public void testMetaAnnotations()
   {
      WBClass<Order> annotatedElement = WBClassImpl.of(Order.class, transformer);
      Set<Annotation> annotations = annotatedElement.getMetaAnnotations(Qualifier.class);
      assert annotations.size() == 1;
      Iterator<Annotation> it = annotations.iterator();
      Annotation production = it.next();
      assert Random.class.equals(production.annotationType());
   }
   
   @Test
   public void testEmpty()
   {
      WBClass<Order> annotatedElement = WBClassImpl.of(Order.class, transformer);
      assert annotatedElement.getAnnotation(Stereotype.class) == null;
      assert annotatedElement.getMetaAnnotations(Stereotype.class).size() == 0;
      WBClass<Antelope> classWithNoAnnotations = WBClassImpl.of(Antelope.class, transformer);
      assert classWithNoAnnotations.getAnnotations().size() == 0;
   }
   
}