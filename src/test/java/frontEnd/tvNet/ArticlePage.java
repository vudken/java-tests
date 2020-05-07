package frontEnd.tvNet;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage {

    private Core core;
    private final By TITLE_ARTICLE_PAGE = By.xpath(".//h1[@class='article-headline']");
    private final By COMMENT_COUNTS_ARRAYS_ARTICLE_PAGE = By.xpath(".//div[contains(@class, 'article-share')]");
    private final By COMMENT_COUNT_ARTICLE_PAGE = By.xpath(".//a[contains(@class, 'article-share__item article-share__item--comments')]");
    private final By COMMENT_COUNT_ARTICLE_PAGE_BUTTON = By.xpath(".//img[@src='/v5/img/icons/comment-v2.svg']");

    public ArticlePage(Core core) {
        this.core = core;
    }

    public String getTitle() {
        return core.findElement(TITLE_ARTICLE_PAGE).getText();
    }

    public int getCommentsValue() {
        if (core.isElementPresent(COMMENT_COUNT_ARTICLE_PAGE)) {
            return core.parseInt(core.findElement(COMMENT_COUNT_ARTICLE_PAGE));
        } else {
            return 0;
        }
    }

    public void getAllCommentsValue() {
        try {
            List<WebElement> list = core.findElements(COMMENT_COUNTS_ARRAYS_ARTICLE_PAGE);
            String[] arr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i).getText();
            }

            String a = arr[0];
            String b = arr[1];
            String[] arrAbove = a.split("\\n");
            String[] arrBelow = b.split("\\n");

            int[] commentsValueAbove = parseIntArray(arrAbove);
            int[] commentsValueBelow = parseIntArray(arrBelow);

            Assertions.assertArrayEquals(commentsValueAbove, commentsValueBelow, "Comments value on article page above and below not the same.");
        } catch (NullPointerException e) {
            System.out.println("There is no comments yet.");
        }
    }

    public int[] parseIntArray(String[] arr) {
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    public void proceedToCommentPage() {
        core.click(core.findElement(COMMENT_COUNT_ARTICLE_PAGE_BUTTON));
    }
}
