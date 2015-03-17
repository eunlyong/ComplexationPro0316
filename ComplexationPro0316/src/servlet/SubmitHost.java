package servlet;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.tdb.TDBFactory;

import dao.*;

/**
 * Servlet implementation class SubmitHost
 */
@WebServlet("/SubmitHost")
public class SubmitHost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitHost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SubmitHost");
		
		String image = "";
		String hostproperty = "";
		String hostvalue = "";
//		String monosaccharideproperty = "";
//		String monosaccharidevalue = "";
		String host = "";
		String fileName = "";
		String hostmonoinfo = "";
		String hostmolfile = "";
		
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
					
				File hostimage = new File(request.getSession().getServletContext().getRealPath("/"+"HostImage"));	
				if(!hostimage.exists()){
					hostimage.mkdir();
				}
				//String path = request.getSession().getServletContext().getRealPath("/"+"File");
				
				//获得文件名 
				fileName = item.getName(); 
				System.out.println("FileName: " + fileName); 
				//该方法在某些平台(操作系统),会返回路径+文件名 
				fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
				
				image = hostimage +"\\"+fileName;
				System.out.println("Image: " + image);
				File file = new File(hostimage +"\\"+fileName); 
					if(!file.exists()){ 
					item.write(file); 
					//将上传图片的名字记录到数据库中 
					
					//resp.sendRedirect("/Dayan/upload.jsp"); 
					} 
				} 
				else
				{
					System.out.println("GET: " + item.getString()); 
					if(item.getFieldName().equals("hostproperty")){
						hostproperty = item.getString();
					}else if(item.getFieldName().equals("hostvalue")){
						hostvalue = item.getString().toLowerCase();
					}else if(item.getFieldName().equals("host")){
						host = item.getString().toLowerCase();
					}else if(item.getFieldName().equals("hostmonoinfo")){
						hostmonoinfo = item.getString();
					}else if(item.getFieldName().equals("hostmolfile")){
						hostmolfile = item.getString().replaceAll("\n",",");
						hostmolfile = hostmolfile.replace("...", "!");
						hostmolfile = hostmolfile.replace("\r", "#");
					}
//					else if(item.getFieldName().equals("monosaccharideproperty")){
//						monosaccharideproperty = item.getString();
//					}else if(item.getFieldName().equals("monosaccharidevalue")){
//						monosaccharidevalue = item.getString().toLowerCase();
//					}
					//System.out.println(item.getFieldName()+":"+item.getString());
				}
			} 
		} catch (Exception e) { 
		e.printStackTrace(); 
		}
		
		System.out.println("Image:" + image);
		//  Sparql  contents  . is not allow for string
		//image = image.replaceAll(".", ",");
		String sparqlpart1 ="";
		for(int i = 0; i < hostproperty.split(",").length; i++){
			sparqlpart1 += " complexation:" + host + " "  + hostproperty.split(",")[i] + " " + hostvalue.split(",")[i] + ". ";
		}
		sparqlpart1 += "complexation:" + host  + " rdf:type complexation:host. ";
		sparqlpart1 += "complexation:" + host  + " complexation:has_name \"" + host + "\"^^xsd:string. ";
		sparqlpart1 += hostmonoinfo;
		System.out.println(sparqlpart1);
		String sparqlpart2="";
		if(!image.equals("")){
			sparqlpart2 = " complexation:" + host + " complexation:has_nmr_spectrum \"" + fileName + "\"^^xsd:string. ";
		}
		if(!hostmolfile.equals("")){
			sparqlpart2 = " complexation:" + host + " complexation:has_mol \"" + hostmolfile + "\"^^xsd:string. ";
		}
		
		String queryString = Variables.prefix + " Insert Data {" + sparqlpart1  + sparqlpart2 + " }" ;
	
		JenaDao dao = new JenaDao();
		//dao.insertDataIntoJena(queryString);
		
	}

}
