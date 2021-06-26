package markup;

import java.util.List;

public class Emphasis extends AbstractElement {
    public Emphasis(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb){
        mask(sb, "*", "*", "MD");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        mask(sb, "<em>", "</em>", "Html");
    }

}