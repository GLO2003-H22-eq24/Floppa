package ulaval.glo2003.floppa.app.annotation.dateformat;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

@Provider
public class DateParameterConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> convertType, Type type, Annotation[] annotations) {
        if (Date.class.equals(convertType)) {
//            DateParameterConverter dateParameterConverter = new DateParameterConverter();
//            for (Annotation annotation: annotations){
//                if (DateFormat.class.equals(annotation.annotationType())){
//                    dateParameterConverter.se
//                }
//            }
            return (ParamConverter<T>) new DateParameterConverter();
        }
        return null;
    }
}
