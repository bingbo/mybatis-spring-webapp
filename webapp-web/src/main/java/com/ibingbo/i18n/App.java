package com.ibingbo.i18n;

import java.text.DateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * App
 *
 * @author zhangbingbing
 * @date 18/1/12
 */
public class App {
    public static void main(String[] args) {
        Locale[] list = DateFormat.getAvailableLocales();
        for (Locale locale : list) {
            System.out.println(locale.toString());
        }
        String language ;
        String country;
        if (args.length != 2) {
            language = "en";
            country = "US";
        } else {
            language = args[0];
            country = args[1];
        }

        Locale locale = new Locale(language, country);
        locale = Locale.SIMPLIFIED_CHINESE;
//        locale = Locale.forLanguageTag("zh_TW");
        ResourceBundle message = ResourceBundle.getBundle("Message", locale);

        System.out.println(message.getString("greetings"));
        System.out.println(message.getString("inquiry"));
        System.out.println(message.getString("farewell"));

    }
}
