package com.samenea.banking.simia.service.validator;

import com.samenea.banking.validator.Violation;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 1:46 PM
 */
public interface Validator<T> {
    List<Violation> validate(T object);
}
