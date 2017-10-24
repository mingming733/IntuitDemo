package com.intuit.service;
import com.intuit.service.helper.DocTagHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marom on 27/09/16.
 */
@Path("mydemo")
public class DocTag {
    private static final String PATH = "/";
    private DocTagHelper docTagHelper = new DocTagHelper();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    // Displays all documents matching the selected tag
    @GET
    @Path("/tag/{tag}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDocByTag(@PathParam("tag") String tag) {
        return docTagHelper.getDocByTag(tag);
    }

    //Displays tags of this document
    @GET
    @Path("/doc/{doc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTagsForDoc(@PathParam("doc") String doc) {
        return docTagHelper.showTags(doc);
    }

    //Displays all available unique tags
    @GET
    @Path("/alltags")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllTags() {
        return docTagHelper.showAllTags();
    }

    //Displays document content
    @GET
    @Path("/content/{doc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showContent(@PathParam("doc") String doc) {
        return docTagHelper.readFile(doc);
    }
}