package com.sample.iBank.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OnLoadAction
 */
public class OnLoadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnLoadAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init() {
        System.out.println( getServletName() + ": initialised" );
        
        try 
        {
        	
        	
        	ServletContext servletContext = getServletContext();
    		String contextPath = servletContext.getRealPath(File.separator);
          
            String propertiespath = contextPath + File.separator + 	"WEB-INF"+File.separator+"classes"+File.separator+"ApplicationResources.properties";
            FileInputStream in = new FileInputStream(propertiespath);
            Properties props = new Properties();
            props.load(in);
            
            in.close();

            FileOutputStream out = new FileOutputStream(propertiespath);
            props.setProperty("Datafilepath", contextPath+"DataFiles");
            props.store(out, null);
            out.close();
           
            
            
    		System.out.println ("Path:"+contextPath);
    		
    		
    		
        }
        catch(Exception ex)
        {
        
        }
        
        
      }

}
