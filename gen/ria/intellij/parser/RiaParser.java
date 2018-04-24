// This is a generated file. Not intended for manual editing.
package ria.intellij.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ria.intellij.psi.RiaTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RiaParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == BRACKET_EXPRESSION) {
      r = bracket_expression(b, 0);
    }
    else if (t == ITEM) {
      r = item(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return riaFile(b, l + 1);
  }

  /* ********************************************************** */
  // PAR_LEFT item* PAR_RIGHT
  // | BRACES_LEFT item* BRACES_RIGHT
  // | BRACKET_LEFT item* BRACKET_RIGHT
  public static boolean bracket_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BRACKET_EXPRESSION, "<bracket expression>");
    r = bracket_expression_0(b, l + 1);
    if (!r) r = bracket_expression_1(b, l + 1);
    if (!r) r = bracket_expression_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PAR_LEFT item* PAR_RIGHT
  private static boolean bracket_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAR_LEFT);
    r = r && bracket_expression_0_1(b, l + 1);
    r = r && consumeToken(b, PAR_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // item*
  private static boolean bracket_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "bracket_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // BRACES_LEFT item* BRACES_RIGHT
  private static boolean bracket_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACES_LEFT);
    r = r && bracket_expression_1_1(b, l + 1);
    r = r && consumeToken(b, BRACES_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // item*
  private static boolean bracket_expression_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "bracket_expression_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // BRACKET_LEFT item* BRACKET_RIGHT
  private static boolean bracket_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_LEFT);
    r = r && bracket_expression_2_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // item*
  private static boolean bracket_expression_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_2_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "bracket_expression_2_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // WHITE_SPACE
  //   | KEYWORD
  //   | SEPARATOR
  //   | COMMENT
  //   | DOC_COMMENT
  //   | IDENTIFIER
  //   | OPERATOR
  //   | NUMBER
  //   | STRING
  //   | bracket_expression
  public static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM, "<item>");
    r = consumeToken(b, WHITE_SPACE);
    if (!r) r = consumeToken(b, KEYWORD);
    if (!r) r = consumeToken(b, SEPARATOR);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, DOC_COMMENT);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, OPERATOR);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = bracket_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item*
  static boolean riaFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "riaFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "riaFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

}
