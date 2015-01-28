package com.samenea.banking.simia.service;

import com.samenea.commons.component.utils.Environment;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/29/13
 * Time: 1:42 PM
 */

public class DefaultDocumentGeneratorTest {

    @InjectMocks
    private DefaultDocumentGenerator documentGenerator;
    @Mock
    private Environment environment;

    private Date date = Calendar.getInstance().getTime();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(environment.getCurrentDate()).thenReturn(date);
    }

    @Test
    public void should_return_timestamp_of_now() {
        Assert.assertEquals(String.valueOf(date.getTime()), documentGenerator.getDocNumber());
    }
}
