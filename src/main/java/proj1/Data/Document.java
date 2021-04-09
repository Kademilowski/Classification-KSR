package proj1.Data;

import org.apache.commons.lang3.*;
import org.apache.lucene.benchmark.utils.ExtractReuters;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {


    //????????????????
    public List<String> articles = new ArrayList<String>();
    //?????????????????????



    public void listFilesForFolder(final File folder) throws IOException {

        //Pattern TAG_REGEX = Pattern.compile("<REUTERS (.+?)</REUTERS>", Pattern.DOTALL);

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                FileInputStream inputStream = new FileInputStream(fileEntry.getPath());
                String everything = new String(Files.readAllBytes(Paths.get(fileEntry.getPath())));


                    for(String it : everything.split("</REUTERS>")){
                        if(!it.trim().isEmpty()){

                            articles.add(it);

                        }

                    }

            }
        }
    }

    public List<String> getArticles() {
        return articles;
    }



}
