package dao;

import java.io.*;

import org.openbabel.*;


public class AssistToolDao {
	Runtime rt = Runtime.getRuntime();
	public String checkmolChangeSdfToMDLmolfile(String file) throws IOException, InterruptedException{
		String line = "";
		String readline = "";
		Process pr = rt.exec( Variables.ToolLocationPath + "checkmol -m " + file);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));

		while ((line = input.readLine()) != null) {

			readline += line + "\n";
			// System.out.println(readline);
		}
		pr.waitFor();
		
		return readline;
	}
	public String checkmolRepresentEachElementWithComma(String file) throws IOException, InterruptedException{
	
		
		Process pr = rt.exec( Variables.ToolLocationPath + "checkmol -aXbH " + file);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));
		String line = "";
		String readline = "";
		while ((line = input.readLine()) != null) {

			readline += line + "\n";
			System.out.println("Chekmol Read: " + readline);
		}
		pr.waitFor();
		
		return readline;
	}
	public String checkmolRepresentEachGroupInBitString( String file) throws IOException, InterruptedException{
	
		Process pr = rt.exec( Variables.ToolLocationPath + "checkmol -axxHb " + file);

		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));
		String line = null, line1 = null, line2 = null;
		String readline = "";
		while ((line = input.readLine()) != null) {

			readline += line + "\n";
		}
		pr.waitFor();
		
		return readline;
	}
	public String matchmolCompareFingerPrintDecimalOutput(String file) throws IOException, InterruptedException{
		
		Process pr = rt.exec( Variables.ToolLocationPath + "matchmol -F " + file
		+ " " + Variables.UsedFilePath + "fpchunk.sdf");
		BufferedReader input = new BufferedReader(new InputStreamReader(
		pr.getInputStream()));
		String bfpnum = "";
		String line = "";
		while ((line = input.readLine()) != null) {
			bfpnum = line;

		}
		pr.waitFor();
		
		return bfpnum;
	}
	public String mol2psChangeMdlfileToPostscript(String file) throws IOException, InterruptedException{
		
		Process pr = rt.exec( Variables.ToolLocationPath + "mol2ps  " + file);

		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));
		String line = null;
		String readline = "";
		while ((line = input.readLine()) != null) {

			readline += line + "\n";
			// System.out.println(readline);
		}
		pr.waitFor();
		
		return readline;
	}
	public void phostscriptChangeToImage(String filelocation, String imageName, String file) throws IOException, InterruptedException{
		File guestimage = new File(filelocation + "/"+"GuestImage");	
		if(!guestimage.exists()){
			guestimage.mkdir();
		}
		Process pr = rt
				.exec( Variables.ToolLocationPath + "gs9.15\\bin\\gswin64 -dSAFER -q -dBATCH -dNOPAUSE -g1000x1000 -r400 "
						+ " -sDEVICE=png16m  -sOutputFile=" + guestimage + "GuestImage/"
						+ imageName + ".png " + file);
		pr.waitFor();
	}
	public String smilesToMol(String smiles) throws IOException{
		System.loadLibrary("openbabel_java");
		
		OBConversion conv = new OBConversion();
		OBMol mol = new OBMol();

		conv.SetInFormat("can");  //inchi
		conv.ReadString(mol, smiles);

		// Print out some general information
		String molstr = "";
		conv.SetOutFormat("mol");
		
		// ========== 14-12-11 Exception not solved ==========
		
		try{
			molstr = conv.WriteString(mol);
			System.out.print("Canonical SMILES: " + molstr);
		}catch(Exception e){
			System.out.println("Exception!!!" + e);
		}
		
		
		return molstr;
		
	}
}
