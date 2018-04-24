package ria.intellij;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class RiaColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
        new AttributesDescriptor("Separator", RiaSyntaxHighlighter.SEPARATOR),
        new AttributesDescriptor("Comment", RiaSyntaxHighlighter.COMMENT),
        new AttributesDescriptor("Keyword", RiaSyntaxHighlighter.KEYWORD),
        new AttributesDescriptor("Number", RiaSyntaxHighlighter.NUMBER),
        new AttributesDescriptor("String", RiaSyntaxHighlighter.STRING),
        new AttributesDescriptor("Identifier", RiaSyntaxHighlighter.IDENTIFIER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return RiaIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new RiaSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "module module1.module;\n" +
            "\n" +
            "typedef maybe = Some number | None ();\n" +
            "\n" +
            "let number? s is string -> maybe =\n" +
            "  try\n" +
            "    Some (number s) as maybe;\n" +
            "  catch NumberFormatException e:\n" +
            "    None () as maybe;\n" +
            "  end;\n" +
            "\n" +
            "let sumArgs args = args |> map number? |> unwrapSome |> fold (+) 0;\n" +
            "\n" +
            "println \"Hello from module.\"\n" +
            "\n" +
            "{\n" +
            "  sumArgs = sumArgs\n" +
            "};";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Ria";
    }
}