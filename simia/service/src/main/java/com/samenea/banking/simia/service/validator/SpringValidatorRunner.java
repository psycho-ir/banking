package com.samenea.banking.simia.service.validator;//import org.slf4j.Logger;

import com.samenea.banking.validator.Violation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 1:49 PM
 */

class SpringValidatorRunner<T extends Validator<K>, K> implements ValidatorRunner {
    private final List<Violation> violations;
    private final K object;
    private final Collection<T> validators;

    public SpringValidatorRunner(K object, Collection<T> validators) {
        this.validators = validators;
        this.violations = new ArrayList<Violation>();
        this.object = object;

    }

    public List<Violation> run() {

        for (Validator<K> validator : validators) {
            violations.addAll(validator.validate(object));
        }

        return violations;
    }

}
