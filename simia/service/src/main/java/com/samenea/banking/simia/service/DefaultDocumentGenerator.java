package com.samenea.banking.simia.service;

import com.samenea.commons.component.utils.Environment;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/29/13
 * Time: 1:39 PM
 */

@Service
public class DefaultDocumentGenerator implements DocumentGenerator {
    @Autowired
    private Environment environment;

    @Override
    public String getDocNumber() {
        return String.valueOf(environment.getCurrentDate().getTime());
    }
}
