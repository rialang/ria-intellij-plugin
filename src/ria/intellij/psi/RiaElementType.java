package ria.intellij.psi;

import com.intellij.psi.tree.IElementType;

import ria.intellij.RiaLanguage;

import org.jetbrains.annotations.*;

public class RiaElementType extends IElementType {
    public RiaElementType(@NotNull @NonNls String debugName) {
        super(debugName, RiaLanguage.INSTANCE);
    }
}