package ria.intellij.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ria.intellij.RiaLanguage;

public class RiaTokenType extends IElementType {
    public RiaTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RiaLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RiaTokenType." + super.toString();
    }
}
