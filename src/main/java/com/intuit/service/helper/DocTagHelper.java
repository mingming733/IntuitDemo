package com.intuit.service.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mming on 10/24/17.
 */
public class DocTagHelper {
    HashMap<String, ArrayList<String>> tagToDoc = new HashMap<String, ArrayList<String>>();
    HashMap<String, ArrayList<String>> docToTag = new HashMap<String, ArrayList<String>>();
    HashMap<String, String> docToPath = new HashMap<String, String>();
    boolean filedLoaded;

    public String getDocByTag(String tag){
        tag = tag.toLowerCase();
        String docs = "No documents with this tag";
        if(!filedLoaded){
            loadFile();
        }
        if(tagToDoc.containsKey(tag)){
            docs = listToString(tagToDoc.get(tag));
        }
        return docs;
    }

    public String showTags(String docName){
        docName = docName.toLowerCase();
        String tags = "No tags in this document";
        if(!filedLoaded){
            loadFile();
        }
        if(docToTag.containsKey(docName)){
            tags = listToString(docToTag.get(docName));
        }
        return tags;
    }

    public String showAllTags(){
        String tags = "";
        if(!filedLoaded){
            loadFile();
        }
        for(String tag : tagToDoc.keySet()){
            tags += tag;
            tags += ",";
        }
        if(tags.length() > 0){
            tags = tags.substring(0, tags.length() - 1);
        }
        return tags;
    }

    public String readFile(String docName){
        if(!filedLoaded){
            loadFile();
        }
        String path = "";
        if(docToPath.containsKey(docName)){
            path = docToPath.get(docName);
        }else{
            return "No such file";
        }
        docName = docName.toLowerCase();
        path += docName;
        path +=".txt";
        StringBuffer stringBuffer = new StringBuffer();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(path).getFile());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            System.out.println("List of documents:");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public boolean loadFile(){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("documents.txt").getFile());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
                processLine(line);
            }
            fileReader.close();
            System.out.println("List of documents:");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        filedLoaded = true;
        return true;
    }

    public void processLine(String line){
        String[] pairs = line.split(",");
        String docName = "";
        for(String pair: pairs){
            String[] keyVaule = pair.split("\\:");
            if(keyVaule[0].toLowerCase().equals("name")){
                docName = keyVaule[1];
            }else if(keyVaule[0].toLowerCase().equals("tag")){
                if(!tagToDoc.containsKey(keyVaule[1])){
                    tagToDoc.put(keyVaule[1], new ArrayList<String>());
                }
                tagToDoc.get(keyVaule[1]).add(docName);
                if(!docToTag.containsKey(docName)){
                    docToTag.put(docName, new ArrayList<String>());
                }
                docToTag.get(docName).add(keyVaule[1]);
            }else if(keyVaule[0].toLowerCase().equals("path")){
                docToPath.put(docName, keyVaule[1]);
            }
        }
    }

    private String listToString(ArrayList<String> strs){
        if(strs == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
            sb.append(",");
        }
        if(sb.length() > 0){
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
