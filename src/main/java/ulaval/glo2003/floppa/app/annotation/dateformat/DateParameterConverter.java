package ulaval.glo2003.floppa.app.annotation.dateformat;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ParamConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParameterConverter implements ParamConverter<Date> {
//    private DateFormat dateFormat =

//    public void setDateFormat(DateFormat format){
//        dateFormat = format;
//    }

    @Override
    public Date fromString(String str) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT);
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new WebApplicationException(e);
        }
    }

    @Override
    public String toString(Date date) {
        return new SimpleDateFormat(DateFormat.DATE_FORMAT).format(date);
    }
}
