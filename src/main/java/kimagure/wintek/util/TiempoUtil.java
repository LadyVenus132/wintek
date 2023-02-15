package kimagure.wintek.util;

import lombok.extern.java.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log
public final class TiempoUtil {

    public final static String formatearFechaStr(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return null == cal ?
                "" : sdf.format(cal.getTime());
    }

    public final static Calendar generarFecha(String fechaStr) {
        Calendar cal = Calendar.getInstance();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.setTime(sdf.parse(fechaStr));
        }catch (ParseException e){
            log.severe(e.getMessage());
            return null;
        }

        return cal;
    }
}
