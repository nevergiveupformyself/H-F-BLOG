package com.hf.service.impl;

import com.hf.dto.Article;
import com.hf.service.IArticleService;
import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Arrays;

/**
 * Created by fjm on 2018/1/11.
 */
@Transactional
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements IArticleService {

    /**
     * 将article中的markdown文本转为html
     *
     * @param article
     * @return
     */
    public String markDown2html(Article article) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[]{
                TablesExtension.create()
        }));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(article.getArticleContent());
        return renderer.render(document);
    }

    public static void main(String[] args) {
        ArticleServiceImpl articleService = new ArticleServiceImpl();
        Article article = new Article();
        article.setArticleContent("Markdown cells support standard Markdown syntax as well as GitHub Flavored Markdown (GFM). Open the preview to see these rendered.\n" +
                "\n" +
                "### Basics\n" +
                "\n" +
                "# H1\n" +
                "## H2\n" +
                "### H3\n" +
                "#### H4\n" +
                "##### H5\n" +
                "###### H6\n" +
                "\n" +
                "---\n" +
                "\n" +
                "*italic*, **bold**, ~~Scratch this.~~\n" +
                "\n" +
                "`inline code`\n" +
                "\n" +
                "### Lists\n" +
                "\n" +
                "1. First ordered list item\n" +
                "2. Another item\n" +
                "  * Unordered sub-list. \n" +
                "1. Actual numbers don't matter, just that it's a number\n" +
                "  1. Ordered sub-list\n" +
                "4. And another item.\n" +
                "\n" +
                "### Quote\n" +
                "\n" +
                "> Peace cannot be kept by force; it can only be achieved by understanding.\n" +
                "\n" +
                "### Links\n" +
                "\n" +
                "[I'm an inline-style link](https://www.google.com)\n" +
                "http://example.com\n" +
                "\n" +
                "You can also create a link to another note: (Note menu -> Copy Note Link -> Paste)\n" +
                "[01 - Getting Started](quiver-note-url/D2A1CC36-CC97-4701-A895-EFC98EF47026)\n" +
                "\n" +
                "### Tables\n" +
                "\n" +
                "| Tables        | Are           | Cool  |\n" +
                "| ------------- |:-------------:| -----:|\n" +
                "| col 3 is      | right-aligned | $1600 |\n" +
                "| col 2 is      | centered      |   $12 |\n" +
                "| zebra stripes | are neat      |    $1 |\n" +
                "\n" +
                "### GFM Task Lists\n" +
                "\n" +
                "- [ ] a task list item\n" +
                "- [ ] list syntax required\n" +
                "- [ ] normal **formatting**, @mentions, #1234 refs\n" +
                "- [ ] incomplete\n" +
                "- [x] completed\n" +
                "\n" +
                "### Inline LaTeX\n" +
                "\n" +
                "You can use inline LaTeX inside Markdown cells as well, for example, $x^2$.\n");
        try {
            String a = articleService.markDown2html(article);
            System.out.println(a);
            FileOutputStream fi = new FileOutputStream("test.html");
            fi.write(a.getBytes());
            fi.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
