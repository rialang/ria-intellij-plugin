package ria.intellij;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ria.intellij.psi.RiaTypes;

public class RiaPairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
        new BracePair(RiaTypes.PAR_LEFT, RiaTypes.PAR_RIGHT, false),
        new BracePair(RiaTypes.BRACES_LEFT, RiaTypes.BRACES_RIGHT, true),
        new BracePair(RiaTypes.BRACKET_LEFT, RiaTypes.BRACKET_RIGHT, false),
    };


    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
