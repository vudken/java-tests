package frontEnd.delfi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class RunTest {

    private Core core = new Core();

    private final Logger LOGGER = LogManager.getLogger(Core.class);

    @Test
    public void delfiTest() {
        final String HOMEPAGE = "rus.frontEnd.delfi.lv";

        core.goToUrl(HOMEPAGE);

        HomePage homePage = new HomePage(core);

        LOGGER.info("Getting needed article from the list on home page.");
        WebElement article = homePage.getArticleById(1);

        LOGGER.info("Getting article title on home page.");
        String titleHomePage = homePage.getTitle(article);

        LOGGER.info("Getting comments value on home page.");
        int commentsValueHomePage = homePage.getCommentsValue(article);

        LOGGER.info("Proceeding to the article page.");
        homePage.proceedToArticlePage(article);

        ArticlePage articlePage = new ArticlePage(core);

        LOGGER.info("Getting article title on article page.");
        String titleArticlePage = articlePage.getTitle();
        assertEquals(titleHomePage, titleArticlePage, "Titles on Home Page and Article Page aren't equals");

        LOGGER.info("Getting comments value on article page.");
        int commentsValueArticlePage = articlePage.getCommentsValue();
        assertEquals(commentsValueHomePage, commentsValueArticlePage, "Comments value on Home Page and Article Page aren't equals");

        LOGGER.info("Getting comments value on article page below.");
        int commentsValueArticlePageBelow = articlePage.getCommentsValueBelow();
        assertEquals(commentsValueArticlePage, commentsValueArticlePageBelow, "Comments value on Article Page in title and under article aren't equals");

        LOGGER.info("Getting comments anonymous and registered users on article page.");
        boolean presenceOfAnonymousRegisteredCommentsValue = true;
        int anonymousRegisteredArticlePage = articlePage.getCommentsAnonymousRegisteredValue();
        if (anonymousRegisteredArticlePage >= 0) {
            assertEquals(commentsValueArticlePageBelow, anonymousRegisteredArticlePage, "Comments value on Article Page under article and in anonymous & registered users aren't equals");
        }else {
            presenceOfAnonymousRegisteredCommentsValue = false;
            System.out.println("Unable to get comments value for anonymous and registered users.");
        }

        LOGGER.info("Proceeding to the comment page.");
        articlePage.proceedToCommentPage();

        CommentPage commentPage = new CommentPage(core);

        LOGGER.info("Getting article title on comment page.");
        String titleCommentPage = commentPage.getTitle();
        assertEquals(titleArticlePage, titleCommentPage, "Titles on Article Page and Comment Page aren't equals");

        LOGGER.info("Getting comments anonymous and registered users on comment page.");
        if (presenceOfAnonymousRegisteredCommentsValue) {
            int CommentsValueCommentPage = commentPage.getCommentsValue();
            assertEquals(anonymousRegisteredArticlePage, CommentsValueCommentPage, "Comments value on Article Page and Comment Page aren't equals");
        }

        core.driver.quit();
    }
}
