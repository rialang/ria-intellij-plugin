// This is a generated file. Not intended for manual editing.
package ria.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ria.intellij.psi.RiaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ria.intellij.psi.*;

public class RiaItemImpl extends ASTWrapperPsiElement implements RiaItem {

  public RiaItemImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RiaVisitor visitor) {
    visitor.visitItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RiaVisitor) accept((RiaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RiaBracketExpression getBracketExpression() {
    return findChildByClass(RiaBracketExpression.class);
  }

}
