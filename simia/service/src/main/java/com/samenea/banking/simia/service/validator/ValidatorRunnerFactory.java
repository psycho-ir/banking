package com.samenea.banking.simia.service.validator;//import org.slf4j.Logger;
//import com.samenea.commons.component.utils.log.LoggerFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 2:43 PM
 */
@Component
public class ValidatorRunnerFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public <T extends Validator<K>, K> ValidatorRunner createValidatorRunner(Class<T> validatorClass, K object) {
        return new SpringValidatorRunner<T, K>(object, findValidators(validatorClass));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private <T extends Validator<K>, K> Collection<T> findValidators(Class<T> validatorClass) {
        return this.applicationContext.getBeansOfType(validatorClass).values();
    }
}
