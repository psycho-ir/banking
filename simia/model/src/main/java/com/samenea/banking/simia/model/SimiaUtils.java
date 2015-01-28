package com.samenea.banking.simia.model;

import com.samenea.commons.component.utils.persian.DateUtil;

import java.util.Date;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 2:02 PM
 */

public class SimiaUtils {

    public final static String SIMIA_TRANSACTION_MANAGER = "simiaTransactionManager";
    public final static String BANK_CODE = "1";


    public static String getCurrentDate(Date date) {
        final String persianDate = DateUtil.toString(date);
        String result = "";
        for (int i = 0; i < persianDate.length(); i++) {
            if (persianDate.charAt(i) > 1700) {
                result += ((char) (persianDate.charAt(i) - 1728));
            } else {
                result += persianDate.charAt(i);
            }
        }
        String[] parts = result.split("/");
        if (parts[1].length() == 1) {
            parts[1] = "0" + parts[1];
        }
        if (parts[2].length() == 1) {
            parts[2] = "0" + parts[2];
        }

        return parts[0] + "/" + parts[1] + "/" + parts[2];
    }

    public static Date getDate(String date) {
        return DateUtil.toEnglishDate(date);
    }
}
