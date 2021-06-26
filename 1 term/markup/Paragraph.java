package markup;

import java.util.List;

public class Paragraph extends AbstractElement{
    public Paragraph(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb){
        mask(sb, "", "", "MD");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        mask(sb, "", "",  "Html");
    }
}