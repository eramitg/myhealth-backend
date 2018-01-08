package today.smarthealthcare.myhealth.ihealth.util;

import java.util.Date;

public class IHealthUtils {
    public static Date toDate(Long seconds) {
        return new Date(1000 * seconds);
    }
}
