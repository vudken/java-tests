package tvNet;

import org.openqa.selenium.By;

public class CommentPage {

    private Core core;
    private final By TITLE_COMMENT_PAGE = By.xpath(".//h1[@class='article-headline']");
    private final By COMMENT_COUNT_COMMENT_PAGE = By.xpath(".//span[contains(@class, 'heading__count')]");

    public CommentPage(Core core) {
        this.core = core;
    }

    public String getTitle() {
        return core.findElement(TITLE_COMMENT_PAGE).getText();
    }

    public int getCommentsValue() {
        if (core.isElementPresent(COMMENT_COUNT_COMMENT_PAGE)) {
            return core.parseInt(core.findElement(COMMENT_COUNT_COMMENT_PAGE));
        } else {
            return 0;
        }
    }
}
