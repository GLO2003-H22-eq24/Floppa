package ulaval.glo2003.floppa.app.annotation.dateformat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface DateFormat {
     String DATE_FORMAT = "yyyy-MM-dd";
    String value() default DATE_FORMAT;
}
