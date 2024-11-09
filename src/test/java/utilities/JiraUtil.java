package utilities;

import java.io.File;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.Issue.SearchResult;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraUtil {

	public JiraClient jira;
	public String project;

	public JiraUtil(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description) {
		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			//fluenntCreate.field(Field.ATTACHMENT, fluenntCreate);
			Issue newIssue = fluentCreate.execute();
			System.out.println("New Issue Created in JIRA: "+ newIssue);
		} catch (JiraException e) {
			e.printStackTrace();
		}
	}
	
	public void createJiraTicket(String issueType, String summary, String description, File screenshot) {
		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			//fluentCreate.field(Field.ATTACHMENT, screenshot);
			//fluentCreate.field(Field.ATTACHMENT, fluentCreate);
			Issue newIssue = fluentCreate.execute();
			System.out.println("New Issue Created in JIRA: "+ newIssue);
			newIssue.addAttachment(screenshot);
			
			
		} catch (JiraException e) {
			e.printStackTrace();
		}
	}

	public SearchResult searchJiraTicket(String summary) throws JiraException {
		Issue.SearchResult srs = jira.searchIssues(project, "summary ~"+summary);
		if(srs.total == 0 ) {
			System.out.println("Issue not exists");
		}
		return srs;
	}
}
