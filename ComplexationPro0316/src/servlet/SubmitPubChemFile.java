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
 * Servlet implementation class SubmitPubChemFile
 */
@WebServlet("/SubmitPubChemFile")
public class SubmitPubChemFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitPubChemFile() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("SubmitPubChemFile");

		String guestName = "";
		String guestFile = "";
		String mol_id = "";
		String fileName = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// String author = req.getParameter("Lover");
		// System.out.println("author:"+author);
		// 为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 开始解析
		// sfu.setFileSizeMax(1024*400);
		// 每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			List<FileItem> items = sfu.parseRequest(request);
			// 区分表单域
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				// isFormField为true，表示这不是文件上传表单域
				if (!item.isFormField()) {
					// ServletContext sctx = getServletContext();
					// 获得存放文件的物理路径
					// upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹

					String path = request.getSession().getServletContext()
							.getRealPath("/" + "File");
					// 获得文件名
					fileName = item.getName();
					// System.out.println(fileName);
					// 该方法在某些平台(操作系统),会返回路径+文件名
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					File file = new File(path + "\\" + fileName);
					guestFile = path + "\\" + fileName;
					System.out.println("Guest File: " + guestFile);
					if (!file.exists()) {
						item.write(file);

						// 将上传图片的名字记录到数据库中

						// resp.sendRedirect("/Dayan/upload.jsp");
					}
				} else {
					System.out.println("GET: " + item.getString());
					if (item.getFieldName().equals("guestname")) {
						guestName = item.getString().toLowerCase();
					}
					System.out.println(item.getFieldName() + ":"
							+ item.getString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new JenaDao().readFileToJena(guestFile); 
		
		String readline = "";
		String readfile = "";
		BufferedReader struc = new BufferedReader(new FileReader(guestFile));
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		AssistToolDao dao = new AssistToolDao();
		while ((readline = struc.readLine()) != null) {

			// readfile += readline + "\n" ;
			if (count1 > count2) {
				count3++;
				System.out.println("CountInternal: " + count3);
				System.out.println("Line: " + readline);

				String smiles = readline.substring(readline.indexOf("\"") + 1,
						readline.lastIndexOf("\""));
				System.out.println("SMILES: " + smiles);

				// ========== 14-12-11 Exception not solved ==========
				
				String mol = dao.smilesToMol(smiles);
				System.out.println("mol: " + mol);

				count2++;
				System.out.println("=========");
			}

			count1++;

			if (readline.trim().contains("Canonical_SMILES")) { // descr:CID100000_Canonical_SMILES
				System.out.println("Property: " + readline);
			} else {
				count2++;
			}

		}
		System.out.println("Count: " + count3);
		
		
		// request.setAttribute("guestFile", guestFile);
		// request.setAttribute("guestName", guestName);
		// request.setAttribute("molId", mol_id);
		// request.getRequestDispatcher("/complex_insert.jsp").forward(request,
		// response);
	}

}
