import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String targetURL = "https://www.roscosmos.ru/";

    public static List<String> getDocumentBody() throws IOException {
        List<String> titleList = new ArrayList<>();
        Document document = Jsoup.connect(targetURL).get();
        Elements aElements = document.select("span.name");
        for(Element el : aElements){
            String title = el.text();
            titleList.add(title);
            //System.out.println(title);
        }
        return titleList;
    }
}
