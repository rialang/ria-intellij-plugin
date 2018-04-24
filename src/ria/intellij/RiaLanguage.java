package ria.intellij;

import com.intellij.lang.Language;

public class RiaLanguage extends Language {
    public static final RiaLanguage INSTANCE = new RiaLanguage();

    private RiaLanguage() {
        super("Ria");
    }
}
