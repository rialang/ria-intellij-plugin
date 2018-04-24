package ria.intellij;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RiaFileType extends LanguageFileType {
    public static final RiaFileType INSTANCE = new RiaFileType();

    private RiaFileType() {
        super(RiaLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Ria file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Ria Language File";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "ria";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return RiaIcons.FILE;
    }
}
