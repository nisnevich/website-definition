package org.nisnevich.machinelearning.websitedownload.model;

import edu.uci.ics.crawler4j.crawler.Page;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * @author Nisnevich Arseniy
 * @version 1.0 (10.12.2016)
 */
public class FullTextCrawlerModel extends AbstractCrawlerModel {

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        String pageCharset = page.getContentCharset();
        File pageFile = new File("temp_data");
        try {
            FileUtils.writeByteArrayToFile(pageFile, page.getContentData());
            Document doc = Jsoup.parse(pageFile, pageCharset);

            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append(doc.select("meta[name=keywords]"));
            contentBuilder.append(" ");
            contentBuilder.append(doc.body().text());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}