package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
