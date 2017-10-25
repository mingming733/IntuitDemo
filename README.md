This Craft_Code_Demo is a REST resource based API to access documents and tags.
Since time is limited and I am a back end engineer, UI is not implemented (Sheweta's instruction also said UI is optional), clickable instruction is given in the index.jsp page. Please use URL listed below and append parameter to URL to test this API.
Unit test are also provided, only test one methods due to limited time.

Assumpiton:
	There is a meta data text file which associate every document with its tags, the meta date is like this:
		Name:doc1,tag:read,tag:red,tag:story,path:/
		Name:doc2,tag:unread,tag:yellow,tag:news,path:/
		Name:doc3,tag:unread,tag:blue,tag:fiction,path:/
		Name:doc4,tag:read,tag:red,tag:story,path:/
		Name:doc5,tag:read,tag:yellow,tag:news,path:/
		Name:doc6,tag:unread,tag:red,tag:story,path:/
		Name:doc7,tag:read,tag:blue,tag:fiction,path:/
	This meta data was stored in the documents.txt under class path, and it will be loaded at the first time this API get called. 
	The test documents resources are also under the class path, they are doc1.txt, doc2.txt ...doc7.txt, every text has one line content in it. 

Steps to test:
	Load the project with Intellij(or Eclipse STS) and run with Tomcat.
	To test "Displays all available unique tags",use URL http://localhost:8080/rest/mydemo/alltags
	To test "Upon click of a tags displays all documents matching the selected tag", use URL http://localhost:8080/rest/	mydemo/tag/red, can replace "red" with other tags like "blue" "read" "unread" "story"
	To test "Upon selecting a document, displays the contents.", use URL http://localhost:8080/rest/mydemo/content/doc1, 	can also repalce "doc1" with "doc2" "doc7"
	Using URL http://localhost:8080/rest/mydemo/doc/doc1 to show tags of this document
	All these URL are bookmarkable.
