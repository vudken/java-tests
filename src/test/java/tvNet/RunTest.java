package tvNet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class RunTest {

    private Core core = new Core();

    private final Logger LOGGER = LogManager.getLogger(Core.class);

    @Test
    public void runTest() {
        final String HOMEPAGE = "rus.tvnet.lv";

        LOGGER.info("Opening browser and maximizing window");
        core.goToUrl(HOMEPAGE);

        HomePage homePage = new HomePage(core);

        LOGGER.info("Getting needed article");
        WebElement article = homePage.getArticleById(2);

        LOGGER.info("Getting article title on Home Page");
        String titleHomePage = homePage.getTitle(article);

        LOGGER.info("Getting comments value on Home Page");
        int commentsValueHomePage = homePage.getCommentsValue(article);

        LOGGER.info("Proceeding to the Article Page");
        homePage.proceedToArticlePage(article);

        ArticlePage articlePage = new ArticlePage(core);

        LOGGER.info("Getting article title on Article Page");
        String titleArticlePage = articlePage.getTitle();

        assertEquals(titleHomePage, titleArticlePage, "Titles on Home Page and Article Page aren't equals");
        LOGGER.info("Getting comments value on Article Page");
        int commentsValueArticlePage = articlePage.getCommentsValue();
        assertEquals(commentsValueHomePage, commentsValueArticlePage, "Comments value on Home Page and Article Page aren't equals");

        LOGGER.info("Getting comments value from all resources on article page and produce assertions.");
        articlePage.getAllCommentsValue();

        LOGGER.info("Proceeding to the Comment Page");
        articlePage.proceedToCommentPage();

        CommentPage commentPage = new CommentPage(core);

        LOGGER.info("Getting article title on Comment Page");
        String titleCommentPage = commentPage.getTitle();
        assertEquals(titleArticlePage, titleCommentPage, "Titles on Article Page and Comment Page aren't equals");

        LOGGER.info("Getting comments value on Comment Page");
        int commentsValueCommentPage = commentPage.getCommentsValue();
        assertEquals(commentsValueArticlePage, commentsValueCommentPage, "Comments value on Article Page and Comment Page aren't equals");

        core.driver.quit();
    }
}
