package uz.pdp.appnewssite.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HuquqniTekshirish {
    String huquq();
}
