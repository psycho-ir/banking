package com.samenea.banking.simia.service.messaging;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/12/13
 * Time: 12:08 PM
 */
@ContextConfiguration("classpath*:context.xml")
public class SimiaMessageResolverIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Autowired(required = true)
    SimiaMessageResolver simiaMessageResolver;

    @Test
    public void should_inject_simiaMessageResolver() {
        Assert.assertNotNull(simiaMessageResolver);
    }

    @Test
    public void t() {
        System.out.println(simiaMessageResolver.resolve("-5057", ""));
    }
}
