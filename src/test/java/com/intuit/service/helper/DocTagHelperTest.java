package com.intuit.service.helper;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.when;

/**
 * Created by mming on 10/24/17.
 */
public class DocTagHelperTest {
    DocTagHelper docTagHelper;
    @BeforeMethod
    public void setUp(){
        docTagHelper = new DocTagHelper();
        HashMap<String, ArrayList<String>> tagToDoc = new HashMap<String, ArrayList<String>>();
        docTagHelper.tagToDoc = tagToDoc;
        tagToDoc.put("red", new ArrayList<String>(Arrays.asList("doc1", "doc2")));
    }

    @org.testng.annotations.Test
    public void testGetDocByTag() throws Exception {
        String docs = docTagHelper.getDocByTag("red");
        Assert.assertEquals(docs, "doc1,doc2,doc1,doc4,doc6");
    }

}