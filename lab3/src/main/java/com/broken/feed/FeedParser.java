package com.broken.feed;
// 
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*; 
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedParser { 

    public static List<Article> parseXML(String xmlData)throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(xmlData); //??
        Document doc = builder.parse(new InputSource(reader));
        NodeList items = doc.getElementsByTagName("item");
        List<Article> articles = new ArrayList<Article>();
        for (int i=0 ; i<items.getLength(); i++) {
            String title = "";
            String description = "";
            String link = "";
            String pubDate = "";
            Node nodo = items.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element elemment = (Element) nodo;
                if(elemment.getElementsByTagName("title").getLength() > 0){
                    title = elemment.getElementsByTagName("title").item(0).getTextContent();
                }
                if(elemment.getElementsByTagName("description").getLength() > 0){
                    description = elemment.getElementsByTagName("description").item(0).getTextContent();
                }
                if(elemment.getElementsByTagName("link").getLength() > 0){
                    link = elemment.getElementsByTagName("link").item(0).getTextContent();
                }
                if(elemment.getElementsByTagName("pubDate").getLength() > 0){
                    pubDate = elemment.getElementsByTagName("pubDate").item(0).getTextContent();
                }
                
                articles.add(new Article(title, description, pubDate , link));

            }

        }
        return articles;
    }

    public static String fetchFeed(String feedURL) throws MalformedURLException, IOException, Exception {

        URL url = new URL(feedURL);
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        
        // TODO: Cambiar el user-agent al nombre de su grupo.  Done 
        // Si todos los grupos usan el mismo user-agent, el servidor puede bloquear las solicitudes.
        connection.setRequestProperty("User-agent", "Broken");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new Exception("HTTP error code: " + status);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        }
    }
}
