package delfi;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArticlePage {

    private Core core;

    private final By TITLE_ARTICLE_PAGE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By COMMENT_COUNT_TITLE_ARTICLE_PAGE = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By COMMENT_COUNT_ARTICLE_PAGE_BELOW = By.xpath(".//p[contains(@class, 'text-counter')]");
    private final By COMMENT_COUNT_ANONYMOUS_REGISTERED = By.xpath(".//span[contains(@class, 'type-cnt')]");
    private final By BTN_READ_COMMENTS_PATH = By.xpath(".//button[contains(@class, 'input-read')]");

    public ArticlePage(Core core) {
        this.core = core;
    }

    public String getTitle() {
        core.wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ARTICLE_PAGE));
        return core.findElement(TITLE_ARTICLE_PAGE).getText();
    }

    public int getCommentsValue() {
        if (core.isElementPresent(COMMENT_COUNT_TITLE_ARTICLE_PAGE)) {
            return core.parseInt(core.driver.findElement(COMMENT_COUNT_TITLE_ARTICLE_PAGE));
        } else {
            return 0;
        }
    }

    public int getCommentsValueBelow() {
        return core.parseInt(core.findElement(COMMENT_COUNT_ARTICLE_PAGE_BELOW));
    }

    public int getCommentsAnonymousRegisteredValue() {
        int commentsValue = -1;
        if (core.isElementPresent(COMMENT_COUNT_ANONYMOUS_REGISTERED)) {
            int anonymous = core.parseInt(core.findElements(COMMENT_COUNT_ANONYMOUS_REGISTERED).get(0));
            int registered = core.parseInt(core.findElements(COMMENT_COUNT_ANONYMOUS_REGISTERED).get(1));
            commentsValue = anonymous + registered;
        }
        return commentsValue;
    }

    public void proceedToCommentPage() {
        if (core.isElementPresent(BTN_READ_COMMENTS_PATH)) {
            core.click(core.findElement(BTN_READ_COMMENTS_PATH));
        } else {
            System.out.println("Unable to proceed to comment page");
            System.exit(-1);
        }
    }
}
