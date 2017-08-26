package org.vladigeras.util;

import java.util.regex.Pattern;

public class ValidationPatterns {
    public static final Pattern AUTHOR_PATTERN = Pattern.compile("[а-яёА-ЯЁa-zA-Z\\s\\-.]+");  //Russian and English literal + '-' + space + '.'
    public static final Pattern GENRE_PATTERN = AUTHOR_PATTERN;
    public static final Pattern TITLE_PATTERN = Pattern.compile("[a-яёА-ЯЁa-zA-Z0-9\\s\\-.]+");   //Russian and English literal + '-' + space + '.' + numbers
}
