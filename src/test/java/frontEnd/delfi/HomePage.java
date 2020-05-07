package frontEnd.delfi;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class  HomePage {

    private Core core;

    public final By TITLE_HOME_PAGE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENT_COUNT_HOME_PAGE = By.xpath(".//a[@class = 'comment-count text-red-ribbon']");
    private final By ALL_ARTICLES = By.tagName("article");

    public HomePage(Core core) {
        this.core = core;
    }

    public WebElement getArticleById(int id) {
        List<WebElement> articles = core.findElements(ALL_ARTICLES);
        articles.removeIf(element -> element.getText().isEmpty());
        Assertions.assertTrue(id > 0 && id <= articles.size(), "There is no article with such index number");
        return articles.get(id - 1);
    }

    public String getTitle(WebElement article) {
        core.wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_HOME_PAGE));
        return article.findElement(TITLE_HOME_PAGE).getText();
    }

    public int getCommentsValue(WebElement article) {
        if (!article.findElements(COMMENT_COUNT_HOME_PAGE).isEmpty()) {
            return core.parseInt(article.findElement(COMMENT_COUNT_HOME_PAGE));
        } else {
            return 0;
        }
    }

    public void proceedToArticlePage(WebElement element) {
        core.click(element.findElement(TITLE_HOME_PAGE));
    }
 }
