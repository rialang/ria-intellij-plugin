package ria.intellij.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import ria.intellij.RiaFileType;
import ria.intellij.RiaLanguage;

import javax.swing.*;

public class RiaFile extends PsiFileBase {
    public RiaFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RiaLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RiaFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Ria File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}