package delfi;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommentPage {

    private Core core;

    private final By TITLE_COMMENT_PAGE = By.cssSelector("div#comments-listing a");
    private final By COMMENT_COUNT_ANONYMOUS_REGISTERED = By.xpath(".//span[contains(@class, 'type-cnt')]");

    public CommentPage(Core core) {
        this.core = core;
    }

    public String getTitle() {
        core.wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_COMMENT_PAGE));
        return core.findElement(TITLE_COMMENT_PAGE).getText();
    }

    public int getCommentsValue() {
        int anonymous = core.parseInt(core.findElements(COMMENT_COUNT_ANONYMOUS_REGISTERED).get(0));
        int registered = core.parseInt(core.findElements(COMMENT_COUNT_ANONYMOUS_REGISTERED).get(1));
        return anonymous + registered;
    }
}
