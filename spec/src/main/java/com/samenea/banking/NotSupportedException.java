package com.samenea.banking;

import com.samenea.commons.component.utils.exceptions.SamenRuntimeException;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/16/13
 * Time: 11:03 AM
 */

public class NotSupportedException extends SamenRuntimeException {
    public NotSupportedException() {
        super();
    }

    public NotSupportedException(String s) {
        super(s);
    }

    public NotSupportedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NotSupportedException(Throwable throwable) {
        super(throwable);
    }
}
