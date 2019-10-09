package springmvctable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller  
public class tableController
{  
    @RequestMapping("/hello")  
    public ModelAndView helloWorld(HttpServletRequest request,HttpServletResponse res)
    {  
    	String a=request.getParameter("t1");
        String b=request.getParameter("t2");
        String c=request.getParameter("t3");
        String d=request.getParameter("t4");
        String e=request.getParameter("b1");
       
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456789");
        	if(e.equals("Insert")) {
        		PreparedStatement st=con.prepareStatement("insert into empreg values(?,?,?,?)");

        		st.setString(1, a);
        		st.setString(2, b);
        		st.setString(3, c);
        		st.setString(4, d);
        
        		st.execute();
        		String message="Inserted";
        		return new ModelAndView("hellopage", "message", message); 
        		}
        	else if(e.equals("Update")) {
        		PreparedStatement st=con.prepareStatement("update empreg set designation=? where empno=?");
        		st.setString(1, d);
        		st.setString(2, a);
        		st.execute();
        		String message="Updated";
        		return new ModelAndView("hellopage", "message", message); 
        	}
        	else if(e.equals("Delete")) {
        		PreparedStatement st=con.prepareStatement("delete from empreg where empno=?");
        		st.setString(1, a);
        		st.execute();
        		String message="Deleted";
        		return new ModelAndView("hellopage", "message", message);
        	}
        	else if(e.equals("Select")) {
        		Statement st1=con.createStatement();
        		ResultSet rs=st1.executeQuery("select * from empreg");  
        		
        		while(rs.next())  
        		System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+" "+rs.getString(4));
        		String message="Displayed";
        		return new ModelAndView("sqldemo", "message", message);
        	}
        	else
        	{  
        		 return new ModelAndView("errorpage", "message","Sorry, username or password error");  
            } 
        	//con.close();
        }
        catch(Exception ae) {
        	ae.printStackTrace();
        }
		return null;
       
    }
    public void destroy() {
    	System.out.println("destroy");
    }

 
     /*if(e.equals("Insert"))
        {  
       
        return new ModelAndView("hellopage", "message", message);  
        } /* 
        else if(c.equals("sub")){  
        	 int message =   a-b;
             return new ModelAndView("hellopage", "message", message);  
        } 
        else if(c.equals("div")){  
       	 int message =   a%b;
            return new ModelAndView("hellopage", "message", message);  
       } 
        else {
        	int message =   a*b;
            return new ModelAndView("hellopage", "message", message);
        }1
     */
     
        }
     
