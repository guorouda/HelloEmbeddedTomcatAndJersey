package com.ron;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

	public static void main(String[] args) throws Exception, LifecycleException{
		new Main().start();

	}

	public void start() throws ServletException, LifecycleException, MalformedURLException {
		
		//Define a folder to hold web application contents.
		String webappDirLocation = "WebContent/";
		Tomcat tomcat = new Tomcat();
		
		//Define port number for the web application
		String webPort = System.getenv("PORT");
		if(webPort == null || webPort.isEmpty()){
			webPort = "8080";
		}
		
		//Bind the port to Tomcat server
		tomcat.setPort(Integer.valueOf(webPort));
		
		//Define a web application context.
		Context context = tomcat.addWebapp("/tomcatembedded", new File(webappDirLocation).getAbsolutePath());
		
		//Define and bind web.xml file location.
		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
		
		tomcat.start();
		tomcat.getServer().await();
		
	}

}
