package markup;

import java.util.List;

public class Strikeout extends AbstractElement{

    public Strikeout(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb){
        mask(sb, "~", "~", "MD");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        mask(sb, "<s>", "</s>", "Html");
    }

}