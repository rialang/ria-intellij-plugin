package ria.intellij;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.LexerBase;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public class RiaLexerAdapter extends LexerBase {
    private static final Logger LOG = Logger.getInstance(RiaLexerAdapter.class);
    private final RiaLexer myFlex;
    private IElementType myTokenType;
    private CharSequence myText;
    private int myTokenStart;
    private int myTokenEnd;
    private int myBufferEnd;
    private int myState;
    private boolean myFailed;

    public RiaLexerAdapter() {
        this(new RiaLexer(null));
    }

    public RiaLexerAdapter(@NotNull FlexLexer flex) {
        super();
        this.myFlex = (RiaLexer)flex;
    }

    public FlexLexer getFlex() {
        return this.myFlex;
    }

    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {

        this.myText = buffer;
        this.myTokenStart = this.myTokenEnd = startOffset;
        this.myBufferEnd = endOffset;
        this.myFlex.reset(this.myText, startOffset, endOffset, initialState);
        this.myTokenType = null;
    }

    public IElementType getTokenType() {
        this.locateToken();
        return this.myTokenType;
    }

    public int getTokenStart() {
        this.locateToken();
        return this.myTokenStart;
    }

    public int getTokenEnd() {
        this.locateToken();
        return this.myTokenEnd;
    }

    public void advance() {
        this.locateToken();
        this.myTokenType = null;
    }

    @NotNull
    public CharSequence getBufferSequence() {
        return this.myText;
    }

    public int getBufferEnd() {
        return this.myBufferEnd;
    }

    protected void locateToken() {
        if (this.myTokenType == null) {
            this.myTokenStart = this.myTokenEnd;
            if (!this.myFailed) {
                try {
                    this.myState = this.myFlex.yystate();
                    this.myTokenType = this.myFlex.advance();
                    this.myTokenEnd = this.myFlex.getTokenEnd();
                } catch (ProcessCanceledException e) {
                    throw e;
                } catch (Throwable t) {
                    this.myFailed = true;
                    this.myTokenType = TokenType.BAD_CHARACTER;
                    this.myTokenEnd = this.myBufferEnd;
                    LOG.warn(this.myFlex.getClass().getName(), t);
                }

            }
        }
    }

    public String toString() {
        return "RiaLexerAdapter for " + this.myFlex.getClass().getName();
    }

    @Override
    public int getState() {
        return myFlex.getState();
        //return getTokenEnd();
    }
}
