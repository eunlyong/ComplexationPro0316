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

import dao.*;

/**
 * Servlet implementation class SubmitMonosaccharide
 */
@WebServlet("/SubmitMonosaccharide")
public class SubmitMonosaccharide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitMonosaccharide() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Submit Monosaccharide");
		
		String monosaccharideproperty = "";
		String monosaccharidevalue = "";
		String monosacchardie = "";
		String monosaccharideName = "";
		String basetype = "";
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
				
				} 
				else
				{
					System.out.println("GET: " + item.getString()); 
					
				    if(item.getFieldName().equals("monosaccharideproperty")){
						monosaccharideproperty = item.getString();
					}else if(item.getFieldName().equals("monosaccharidevalue")){
						monosaccharidevalue = item.getString().toLowerCase();
					}else if(item.getFieldName().equals("monosaccharide")){
						monosaccharideName = item.getString().toLowerCase();
						monosacchardie = "complexation:" + monosaccharideName;
					}else if(item.getFieldName().equals("basetype")){
						basetype =  "complexation:" + item.getString().toLowerCase();
					}
					System.out.println(item.getFieldName()+":"+item.getString());
				}
			} 
		} catch (Exception e) { 
		e.printStackTrace(); 
		}
		String sparqlpart1 = "";
		
		
			for(int j = 0; j < monosaccharideproperty.split(",").length; j++ ){
				System.out.println("MONO:" + monosaccharidevalue.split(",")[j].trim() + ":");
				if (!monosaccharidevalue.split(",")[j].trim().equals("")) {
				if(j == 0){
					sparqlpart1 += " _:alias " + 
							monosaccharideproperty.split(",")[0] + " "
							+ monosaccharidevalue.split(",")[0] + ".";
					sparqlpart1 += " _:alias " + 
							monosaccharideproperty.split(",")[1] + " "
							+ monosaccharidevalue.split(",")[1] + ".";
				}else if(j == 2){
					sparqlpart1 += monosacchardie + 
							" glycan:has_basetype " + basetype + " . " + basetype + " "  + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j ==3|| j==4 ||j ==5 ||j ==6 || j==7){
					sparqlpart1 += basetype + " " +
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if( j ==8){
					sparqlpart1 += " _:conf " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j ==9){
					sparqlpart1 += " _:conf " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j == 10){
					sparqlpart1 += "_:modif " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j == 12){
					sparqlpart1 += " _:modif " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if( j == 13){
					sparqlpart1 += monosaccharidevalue.split(",")[14] + " " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j == 14){
					sparqlpart1 += monosaccharidevalue.split(",")[14] + " glycan:substituent_linkage _:sublinkage. _:sublinkage " 
							 + monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else if(j == 15){
					sparqlpart1 += " _:sublinkage " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}else{
					
					sparqlpart1 += monosacchardie + " " + 
							monosaccharideproperty.split(",")[j] + " "
							+ monosaccharidevalue.split(",")[j] + ". ";
				}
				}
				
			}
			System.out.println(sparqlpart1);
			if(sparqlpart1.contains("_:alias")){
				sparqlpart1 += monosacchardie + " glycan:has_alias _:alias. ";
			}
			if(sparqlpart1.contains("_:conf")){
				sparqlpart1 += basetype + " glycan:has_configuration _:conf. " ;
			}
			if(sparqlpart1.contains("_:modif")){
				sparqlpart1 += basetype + " glycan:has_core_modification _:modif. " ;
			}
			
			//sparqlpart1 += " complexation:" + host + " complexation:has_nmr_spectrum \"" + fileName + "\"^^xsd:string. ";
		sparqlpart1 += monosacchardie + " rdf:type glycan:monosaccharide. " + basetype + " rdf:type glycan:basetype. ";	
		sparqlpart1 += monosacchardie + " complexation:has_name \"" + monosaccharideName + "\"^^xsd:string. ";
		//String sparqlpart1 = " complexation:" + host + " complexation:has_nmr_spectrum \"" + fileName + "\"^^xsd:string. ";
		String queryString = Variables.prefix + " Insert Data {" + sparqlpart1  + " }" ;
	
		JenaDao dao = new JenaDao();
		dao.insertDataIntoJena(queryString);
		
		
	}

}
