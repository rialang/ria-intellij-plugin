package ria.intellij;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ria.intellij.psi.RiaTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class RiaSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("RIA_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("RIA_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("RIA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("RIA_DOC_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("RIA_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("RIA_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("RIA_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = createTextAttributesKey("RIA_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey PARAMETRIC = createTextAttributesKey("RIA_PARAMETRIC", DefaultLanguageHighlighterColors.PARAMETER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[] {BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[] {SEPARATOR};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[] {IDENTIFIER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[] {COMMENT};
    private static final TextAttributesKey[] DOC_COMMENT_KEYS = new TextAttributesKey[] {DOC_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[] {KEYWORD};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[] {NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[] {STRING};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RiaLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if(tokenType == null) {
            return BAD_CHAR_KEYS;
        }
        if(tokenType.equals(RiaTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if(tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else if(tokenType.equals(RiaTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        } else if(tokenType.equals(RiaTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if(tokenType.equals(RiaTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if(tokenType.equals(RiaTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if(tokenType.equals(RiaTypes.STRING)) {
            return STRING_KEYS;
        } else if(tokenType.equals(RiaTypes.DOC_COMMENT)) {
            return DOC_COMMENT_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}