package com.samenea.banking.validator;

import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/12/13
 * Time: 11:26 AM
 */

public abstract class AbstractViolation implements Violation {

    @Override
    public String toString() {
        return String.format("Violtion: %s - Error Code: %s", this.getDescription(), this.getCode());
    }
}
