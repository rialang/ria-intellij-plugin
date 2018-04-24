// This is a generated file. Not intended for manual editing.
package ria.intellij.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ria.intellij.psi.impl.*;

public interface RiaTypes {

  IElementType BRACKET_EXPRESSION = new RiaElementType("BRACKET_EXPRESSION");
  IElementType ITEM = new RiaElementType("ITEM");

  IElementType BRACES_LEFT = new RiaTokenType("BRACES_LEFT");
  IElementType BRACES_RIGHT = new RiaTokenType("BRACES_RIGHT");
  IElementType BRACKET_LEFT = new RiaTokenType("BRACKET_LEFT");
  IElementType BRACKET_RIGHT = new RiaTokenType("BRACKET_RIGHT");
  IElementType COMMENT = new RiaTokenType("COMMENT");
  IElementType DOC_COMMENT = new RiaTokenType("DOC_COMMENT");
  IElementType IDENTIFIER = new RiaTokenType("IDENTIFIER");
  IElementType KEYWORD = new RiaTokenType("KEYWORD");
  IElementType NUMBER = new RiaTokenType("NUMBER");
  IElementType OPERATOR = new RiaTokenType("OPERATOR");
  IElementType PAR_LEFT = new RiaTokenType("PAR_LEFT");
  IElementType PAR_RIGHT = new RiaTokenType("PAR_RIGHT");
  IElementType SEPARATOR = new RiaTokenType("SEPARATOR");
  IElementType STRING = new RiaTokenType("STRING");
  IElementType WHITE_SPACE = new RiaTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == BRACKET_EXPRESSION) {
        return new RiaBracketExpressionImpl(node);
      }
      else if (type == ITEM) {
        return new RiaItemImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
