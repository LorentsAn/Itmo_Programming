package markup;

public class Text implements Markable {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
    public void toHtml(StringBuilder sb) {
        sb.append(text);
    }
}
