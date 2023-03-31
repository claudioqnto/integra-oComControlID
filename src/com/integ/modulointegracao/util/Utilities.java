
package com.integ.modulointegracao.util;

import java.util.Calendar;

/**
 *
 * Cláudio Nascimento
 */
public class Utilities {
        /**
     * Método que retorna a Data/Hora do sistema.
     *
     * @return
     */
    public String hourMoment() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.YEAR) + " às " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND);
    }
}
