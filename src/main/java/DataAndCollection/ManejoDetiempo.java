package DataAndCollection;

import java.util.Date;

public class ManejoDetiempo {
    public static void manejarTiempo() throws Exception {
        Date date = new Date();
        long consultaOriginal = ManejoDeDB.getTime();
        if (date.getTime() > consultaOriginal + 240000) {
            ManejoDeDB.updateTime(date.getTime());
        }
    }
}
