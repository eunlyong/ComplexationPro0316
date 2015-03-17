package servlet;



import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Complexation;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.tdb.TDBFactory;

import dao.*;

/**
 * Servlet implementation class SubmitComplexation
 */
@WebServlet("/SubmitComplexation")
public class SubmitComplexation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitComplexation() {
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
		System.out.println("SubmitComplexation");
		
		String sparqlpart = "";
		String image = "";
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
				
				File complexationimage = new File(request.getSession().getServletContext().getRealPath("/"+"ComplexationImage"));	
				if(!complexationimage.exists()){
					complexationimage.mkdir();
				}
				//String path = request.getSession().getServletContext().getRealPath("/"+"File");
				
				//获得文件名 
				fileName = item.getName(); 
				System.out.println(fileName); 
				//该方法在某些平台(操作系统),会返回路径+文件名 
				fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
				//String fileName = 
				image = complexationimage +"\\"+fileName;
				System.out.println("Image: " + image);
				File file = new File(complexationimage +"\\"+fileName); 
					if(!file.exists()){ 
					item.write(file); 
					//将上传图片的名字记录到数据库中 
					
					//resp.sendRedirect("/Dayan/upload.jsp"); 
					} 
				} 
				else
				{
					System.out.println("GET: " + item.getString()); 
					if(item.getFieldName().equals("sparqlpart")){
						sparqlpart = item.getString().toLowerCase();
					}else{
						//complexation = item.getClass();
					}
					//System.out.println(item.getFieldName()+":"+item.getString());
				}
			} 
		} catch (Exception e) { 
		e.printStackTrace(); 
		}
		
		

		System.out.println("image" + image);
	
		
		// resource uri generation
		
		for(int i = 0; i < Variables.complexation_insert_sparql.split(",").length; i++){
			if(i == 4 || i == 5 || i ==6 || i == 18 || i == 19 || i ==20 || i ==21 || i ==22 ){
				
			}
			else{
				//queryValue += "complexation:complexation1" + Variables.complexation_insert_sparql.split(",")[i] + value.split(",")[count] + ".";
			}
		}
		String queryvalue = "";
		System.out.println(sparqlpart.split(",").length);
		for(int i = 0; i < sparqlpart.split(",").length; i++){
			if(!sparqlpart.split(",")[i].equals("")){
				if( i == 0){
					queryvalue = "";
				}
				else if(i == 5 || i==6 || i==7 || i ==8){
					queryvalue += "_:condition " + sparqlpart.split(",")[i] + ". ";
				}
				else if(i == 9 || i==10 || i==11 ||i ==12 || i ==13){
					queryvalue += "_:nmr " + sparqlpart.split(",")[i] + ". ";
				}else{
					queryvalue += sparqlpart.split(",")[0] + " " + sparqlpart.split(",")[i] + ". ";
				}
				
				
				
			}
			
			
		}
		if(!fileName.equals("")){
			queryvalue += " _:nmr complexation:nmr_spectrum \"" + fileName + "\"^^xsd:string. ";
		}
		if(queryvalue.contains("_:condition")){
			queryvalue += sparqlpart.split(",")[0] + " complexation:used_condition _:condition. ";
		}
		if(queryvalue.contains("_:nmr")){
			queryvalue += sparqlpart.split(",")[0] + " complexation:has_evidence _:nmr. ";
		}
		System.out.println("Image:" + image);
		//  Sparql  contents  . is not allow for string
		//image = image.replaceAll(".", ",");
		
		
		String queryString = Variables.prefix + " Insert Data {" +sparqlpart.split(",")[0] + 
				" rdf:type complexation:complexation. " +  queryvalue + "}" ;
		System.out.println("Complexation SPARQL Insert: " + queryString);
		
		JenaDao dao = new JenaDao();
		dao.insertDataIntoJena(queryString);
		
		
	}

}
