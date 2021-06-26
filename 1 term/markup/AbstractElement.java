package markup;

import java.util.List;

public abstract class AbstractElement implements Markable {
    protected List<Markable> content;

    public AbstractElement(List<Markable> content) {
        this.content = content;
    }

    public void mask(StringBuilder sb, String border, String borderEnd, String operation) {
        sb.append(border);
        for (Markable elem : content) {
            if (operation.equals("MD")) {
                elem.toMarkdown(sb);
            } else {
                elem.toHtml(sb);
            }

        }
        sb.append(borderEnd);
    }
}
