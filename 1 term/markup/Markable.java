package markup;

public interface Markable {
    void toMarkdown(StringBuilder sb);
    void toHtml(StringBuilder sb);
}