package com.practice.shop.web.controller.config;

import com.practice.shop.model.Language;
import com.practice.shop.model.LanguageLevel;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToLanguage());
//        registry.addConverter(new StringToLanguageLevel());
//    }
//
//}
//
//class StringToLanguage implements Converter<String, Language> {
//
//    public Language convert(String source) {
//        System.out.println(Language.valueOf(source));
//        return Language.valueOf(source);
//    }
//
//}
//class StringToLanguageLevel implements Converter<String, LanguageLevel> {
//
//    public LanguageLevel convert(String source) {
//        return Arrays.stream(LanguageLevel.values())
//                .filter(languageLevel ->
//                                languageLevel.ordinal() == Integer.parseInt(source)
//                        )
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("illegal level"));
//    }
//
//}
