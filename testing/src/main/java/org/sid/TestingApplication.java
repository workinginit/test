package org.sid;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.sonarqube.ws.Common;
import org.sonarqube.ws.Issues;
import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;
import org.sonarqube.ws.client.issue.SearchWsRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TestingApplication implements CommandLineRunner{

	static String resourcekey = "com.company.projectname:moustachetest";     

	
	public static void main(String[] args)  {
		SpringApplication.run(TestingApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	/*Svn svn = new Svn();
		//On definis l'url du dépot subversion
		svn.setUrl("https://svn.riouxsvn.com/testvermegone");
		//On définis l'identifiant pour accèder au dépot (si l'accès est anonyme, ne rien définir)
		svn.setLogin("hedihunter");
		//On définis le mot de passe pour accèder au dépot (si l'accès est anonyme, ne rien définir)
		svn.setPassword("Hedihunter12");
 
		
		
		System.out.println("dd"+svn.connect());
		
		
	/*
		String url = "https://subversion.assembla.com/svn/mycompanyvermeg^mycompanyvermeg-first-space/trunk/thymsecure";
		String username = "hedivermeg"; 
		String password = "Hedihunter12";
		 
	
	        final List<Commit> ret = new ArrayList<Commit>();
	        SVNURL svnUrl = SVNURL.parseURIDecoded(url);
	        SVNClientManager clientManager = SVNClientManager.newInstance(new DefaultSVNOptions(), username, password);
	        SVNLogClient logClient = clientManager.getLogClient();
	        */
	

		//on se connect au svn
		/*if(svn.connect()){
			
			List<SvnEntry> tree = svn.list("src");
			for (int i=0; i< tree.size();i++ ) {
				System.out.println("treee"+tree.size());
				SvnEntry entry = tree.get(i);
				System.out.println("countiiiiiiiiiiiiiii");
                System.out.println(entry.toString());
      }

		}else{
			System.out.println("connexion impossible");
		}*/
       

		//sonar here
        HttpConnector httpConnector = HttpConnector.newBuilder().url("http://localhost:7223").credentials("admin", "admin").token("a0f08c8a2e9c1fa424d3da007315268e5b5a1031").build();
        WsClient wsClient = WsClientFactories.getDefault().newClient(httpConnector);
        
        
        SearchWsRequest searchWsRequest = new SearchWsRequest();
 
        
        ArrayList<String> projectKeys = new ArrayList<>();
        projectKeys.add("moustachetest");
        
        
        searchWsRequest.setProjectKeys(projectKeys);
        
        Issues.SearchWsResponse response = wsClient.issues().search(searchWsRequest);
        //here
        
        
        
        
        
        
        
       // System.out.println("================>>>>>>>"+ response.getIssuesList());
        
        // STD Out Paging Object
        Common.Paging paging = response.getPaging();
        System.out.println("------------------------------------------------------------");
        System.out.println(ToStringBuilder.reflectionToString(paging, ToStringStyle.JSON_STYLE));
        System.out.println("------------------------------------------------------------");

        // STD Out issue
        List<Issues.Issue> issuesList = response.getIssuesList();
        for (Issues.Issue issue : issuesList) {
            System.out.println("------------------------------------------------------------");
            System.out.println("z"+"z");
            System.out.println(issue.getDebt() +" **" +issue);
            System.out.println("------------------------------------------------------------");
        }
	}
}
