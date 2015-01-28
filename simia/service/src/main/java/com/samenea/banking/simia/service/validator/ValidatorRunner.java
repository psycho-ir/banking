package com.samenea.banking.simia.service.validator;

import com.samenea.banking.validator.Violation;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 3:15 PM
 */
public interface ValidatorRunner {
    List<Violation> run();
}
