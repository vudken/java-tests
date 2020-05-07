package frontEnd.tvNet;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

public class HomePage {

    private Core core;
    private final By ALL_ARTICLES = By.tagName("article");
    private final By TITLE_HOME_PAGE = By.xpath(".//span[@class='list-article__headline']");
    private final By COMMENT_COUNT_HOME_PAGE = By.xpath(".//span[@class='list-article__comment section-font-color']");
    private final By ARTICLE_HEADLINE = By.className("list-article__headline");

    public HomePage(Core core) {
        this.core = core;
    }

    public WebElement getArticleById(int id) {
        List<WebElement> articles = core.findElements(ALL_ARTICLES);
        Assertions.assertTrue(id > 0 && id <= articles.size(), "There is no article with such index number");
        return articles.get(id - 1);
    }

    public String getTitle(WebElement element) {
        String title = element.findElement(TITLE_HOME_PAGE).getText();
        boolean isContains = Pattern.compile("\\(\\d+\\)").matcher(title).find();
        if (isContains) {
            title = title.replaceAll("\\(\\d+\\)", "");
        }
        return title;
    }

    public int getCommentsValue(WebElement element) {
        if (!element.findElements(COMMENT_COUNT_HOME_PAGE).isEmpty()) {
            return core.parseInt(element.findElement(COMMENT_COUNT_HOME_PAGE));
        } else {
            return 0;
        }
    }

    public void proceedToArticlePage(WebElement element) {
        core.click(element.findElement(ARTICLE_HEADLINE));
    }
}
