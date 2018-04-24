package ria.intellij;

import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import ria.intellij.psi.RiaTypes;
import com.intellij.psi.TokenType;
import java.util.ArrayList;

%%

%class RiaLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return null;
%eof}

%{
    boolean docComment = false;
    int inString = 0;
    ArrayList<Integer> braceCount = new ArrayList<>();

    public int getState() {
       return yystate() + inString;
    }
%}

CRLF=\R
WHITE_SPACE=[\ \r\n\t\f]
END_OF_LINE_DOC_COMMENT=("///")[^\r\n]*
END_OF_LINE_COMMENT=("//")[^\r\n]*
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_\']*

// Keywords
KEYWORD="and" | "b_and" | "b_or" | "div" | "in" | "not" | "or" |
                      "shl" | "shr" | "xor" | "instanceof" | "module" | "program" | "as" | "case" | "cast" | "catch" | "class" |
                      "classOf" | "done" | "do" | "elif" | "else" | "end" | "extends" |
                      "fall" | "finally" | "for" | "get" | "if" | "import" | "is" | "latch" | "let" | "load" |
                      "loop" | "new" | "unbind" | "of" | "set" | "then" | "try" |
                      "typedef" | "var" | "with"

SEPARATORS=[;,:=]

// Operators
DOT="."
ASSIGN_OPERATOR=":="|"="
OPERATOR="|>" | "+" | "-" | "*" | "/" | "$" | "#"

DIGIT=[0-9]
EXPONENT=[eE]

%state YYINITIAL
%xstate ML_COMMENT
%xstate DQ_STRING
%xstate SQ_STRING

%%

// EOF Rules

<ML_COMMENT><<EOF>> { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
<DQ_STRING><<EOF>> { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
<SQ_STRING><<EOF>> { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

// Comments
<YYINITIAL>"/**"                  { yybegin(ML_COMMENT); docComment = true; }
<YYINITIAL>"/*"                   { yybegin(ML_COMMENT); docComment = false; }
<ML_COMMENT>"*/"                { yybegin(YYINITIAL); return docComment ? RiaTypes.DOC_COMMENT : RiaTypes.COMMENT; }
<ML_COMMENT>[^\*] {}
<ML_COMMENT>"*"[^\/] {}
<ML_COMMENT>[\R]            { }
<ML_COMMENT>[^] { yypushback(yylength()); yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

// Single Quoted Strings
<SQ_STRING>([^']|[\r\n]|"''")+ {}
<SQ_STRING>"'" { yybegin(YYINITIAL); return RiaTypes.STRING; }

// String start
<YYINITIAL>"\""          { yybegin(DQ_STRING); }
<YYINITIAL>"'"           { yybegin(SQ_STRING); }

// Double Quoted Strings
<DQ_STRING>"\""                { yybegin(YYINITIAL); return RiaTypes.STRING; }
<DQ_STRING>"${"                { inString++; braceCount.add(0, 0); yybegin(YYINITIAL); yypushback(2); return RiaTypes.STRING; }
<DQ_STRING>[^\"] {}
<DQ_STRING>"\\\"" {}
<DQ_STRING>"\\"[\r\n]+[\ \t]+"\"" {}
<DQ_STRING>[\r\n]            { }
<DQ_STRING>[^] { yypushback(yylength()); yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

// TODO: Lex `identifier`, ``identifier`` and `a (parametric types) properly

// TODO: Fix for hex/octal
// TODO: Number parsing is not exact, will still allow invalid numbers with alpha suffixes
{DIGIT}+"."{DIGIT}* {EXPONENT} {DIGIT}* "." {DIGIT}* { return TokenType.BAD_CHARACTER; }
{DIGIT}+"."{DIGIT}* {EXPONENT} {DIGIT}* { return RiaTypes.NUMBER; }
{DIGIT}+"."{DIGIT}* { return RiaTypes.NUMBER; }

{DIGIT}+{EXPONENT} {DIGIT}* "." {DIGIT}* { return TokenType.BAD_CHARACTER; }
{DIGIT}+{EXPONENT} {DIGIT}* { return RiaTypes.NUMBER; }
{DIGIT}+ { return RiaTypes.NUMBER; }

{KEYWORD} { return RiaTypes.KEYWORD; }
{SEPARATORS} { return RiaTypes.SEPARATOR; }

{DOT} { return RiaTypes.SEPARATOR; }

{END_OF_LINE_DOC_COMMENT}                   { return RiaTypes.DOC_COMMENT; }
{END_OF_LINE_COMMENT}                       { return RiaTypes.COMMENT; }
{IDENTIFIER}                                { return RiaTypes.IDENTIFIER; }
{ASSIGN_OPERATOR}                           { return RiaTypes.OPERATOR; }
{OPERATOR}                                  { return RiaTypes.OPERATOR; }

{CRLF}({CRLF}|{WHITE_SPACE})+               { return TokenType.WHITE_SPACE; }
{WHITE_SPACE}+                              { return TokenType.WHITE_SPACE; }
({CRLF}|{WHITE_SPACE})+                     { return TokenType.WHITE_SPACE; }

"{" {
    if(braceCount.size() > 0)
        braceCount.set(0, braceCount.get(0) + 1);
    return RiaTypes.BRACES_LEFT;
}

"}" {
    if(braceCount.size() > 0) {
        braceCount.set(0, braceCount.get(0) - 1);
        if(braceCount.get(0) <= 0 && inString > 0) {
            braceCount.remove(0);
            inString--;
            yybegin(DQ_STRING);
        }
    }
    return RiaTypes.BRACES_RIGHT;
}

"[" { return RiaTypes.BRACKET_LEFT; }
"]" { return RiaTypes.BRACKET_RIGHT; }

"(" { return RiaTypes.PAR_LEFT; }
")" { return RiaTypes.PAR_RIGHT; }

. { return TokenType.WHITE_SPACE; }
