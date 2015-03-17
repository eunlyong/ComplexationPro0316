package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.tdb.TDBFactory;

import dao.*;

/**
 * Servlet implementation class SubmitGuest
 */
@WebServlet("/SubmitGuest")
public class SubmitGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitGuest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {
							// TODO Auto-generated method stub
		
		System.out.println("SubmitGuest");
		
		String guestName = "";
		String guestFile = "";
		String mol_id = "";
		String fileName = "";
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8"); 
		//String author = req.getParameter("Lover");
		//System.out.println("author:"+author);
		//为解析类提供配置信息 
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		//创建解析类的实例 
		ServletFileUpload sfu = new ServletFileUpload(factory); 
		//开始解析 
		//sfu.setFileSizeMax(1024*400); 
		//每个表单域中数据会封装到一个对应的FileItem对象上 
		try { 
		List<FileItem> items = sfu.parseRequest(request); 
			//区分表单域 
			for (int i = 0; i < items.size(); i++) { 
			FileItem item = items.get(i); 
			//isFormField为true，表示这不是文件上传表单域 
				if(!item.isFormField()){ 
				//ServletContext sctx = getServletContext(); 
				//获得存放文件的物理路径 
				//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹 
				File guestfiles = new File(request.getSession().getServletContext().getRealPath("/"+"GuestFile"));	
				if(!guestfiles.exists()){
					guestfiles.mkdir();
				}
				//String path = request.getSession().getServletContext().getRealPath("/"+"File");
				//获得文件名 
				fileName = item.getName(); 
				//System.out.println(fileName); 
				//该方法在某些平台(操作系统),会返回路径+文件名 
				fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
				File file = new File(guestfiles +"\\"+fileName); 
				guestFile = guestfiles +"\\"+fileName;
				System.out.println("Guest File: " + guestFile);
					//if(!file.exists()){ 
					item.write(file); 
					
					//将上传图片的名字记录到数据库中 
					
					//resp.sendRedirect("/Dayan/upload.jsp"); 
					//} 
				} 
				else
				{
					System.out.println("GET: " + item.getString()); 
					if(item.getFieldName().equals("guestname")){
						guestName = item.getString().toLowerCase();
					}
					System.out.println(item.getFieldName()+":"+item.getString());
				}
			} 
		} catch (Exception e) { 
		e.printStackTrace(); 
		}
		
		String readline = "";
		String readfile = "";
		
		BufferedReader struc = new BufferedReader(new FileReader(guestFile));
		while((readline = struc.readLine()) != null) {
	        	
	       	 readfile += readline + "\n" ;
	    }
		
		//mol_id = readfile.split("")[readfile.split(":").length-1].split("\n")[2];
	    mol_id = readfile.split(">")[2].substring(1, readfile.split(">")[2].indexOf("\n", 2));
	    
	    mol_id = mol_id.trim();
		System.out.println("molid:==" + mol_id + "=======");

		request.setAttribute("guestFile", guestFile);
		request.setAttribute("guestName", guestName);
		request.setAttribute("molId", mol_id);
		request.getRequestDispatcher("/dist/example/insert_guest.jsp").forward(request, response);
		
//		System.out.println("SendFile:" + sendFile);
//		Dao dao = new Dao();
//		dao.guestInfoIntoJena(sendFile, "fisetin");
		 
	}				

}
