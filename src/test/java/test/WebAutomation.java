package test;

import net.serenitybdd.jbehave.SerenityStories;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.apache.commons.lang.SystemUtils.JAVA_IO_TMPDIR;

public class WebAutomation extends SerenityStories {

    final static Logger LOGGER = Logger.getLogger(String.valueOf(WebAutomation.class));


    public WebAutomation() throws IOException {

        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverPath = System.getProperty(JAVA_IO_TMPDIR);
        // System.setProperty("logFilePath",System.getProperty("user.dir")+"/log/log.out");
        //if (LOGGER.isDebugEnabled()) {
        LOGGER.info("driverPath::" + driverPath);
        //}

        Embedder embedder = configuredEmbedder();
        embedder.useStoryRunner(new StoryRunner());
        embedder.embedderControls()
                .useStoryTimeoutInSecs(500000).doIgnoreFailureInStories(true).doIgnoreFailureInView(true);

        String metaFilter = System.getProperty("metafilter");
        if (StringUtils.isNotEmpty(metaFilter)) {
            embedder.useMetaFilters(Arrays.asList(System.getProperty("metafilter")));
            embedder.configuration().storyControls().doIgnoreMetaFiltersIfGivenStory(true);
        }

        String storyFilter = System.getProperty("storyfilter");
        //String storyFilter = System.getProperty("story name");
        if (StringUtils.isNotEmpty(storyFilter) && !storyFilter.contains("/")) {
            findStoriesCalled(storyFilter);
        } else {
            findStoriesIn(storyFilter);
        }
    }
}