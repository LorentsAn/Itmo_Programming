package markup;

import java.util.List;

public class Strong extends AbstractElement {

    public Strong(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        mask(sb, "__", "__", "MD");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        mask(sb, "<strong>", "</strong>", "Html");
    }

}