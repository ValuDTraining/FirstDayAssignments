package umesh_FirstDay;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileOperations {
	Double TOT_RECON_DIFF=0.0,TOT_RECON=0.0;
	static BufferedReader br,b,bpdf;
	public static void openFile() {
		try {
			br= new BufferedReader(new FileReader("resources/input_file_csv_edited_1.csv"));
			System.out.println("File open success");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double calculateTOT_RECON_DIFF() {
		String[] arr;
		String line="";
		try {
			br.readLine();
			while((line=br.readLine())!= null){
				if(line.contains("|")){
					arr=line.split("\\|");
					TOT_RECON_DIFF=TOT_RECON_DIFF+Double.parseDouble(arr[4]);
					TOT_RECON=TOT_RECON+Double.parseDouble(arr[3]);
				}
				else if (line.contains(";")) {
					arr=line.split("\\;");
					TOT_RECON_DIFF=TOT_RECON_DIFF+Double.parseDouble(arr[4]);
					TOT_RECON=TOT_RECON+Double.parseDouble(arr[3]);
				}
				else if (line.contains(":")) {
					arr=line.split("\\:");
					TOT_RECON_DIFF=TOT_RECON_DIFF+Double.parseDouble(arr[4]);
					TOT_RECON=TOT_RECON+Double.parseDouble(arr[3]);
				}
				else{
					String s=line.substring(33, 44);
					String s1=line.substring(23, 33);

					TOT_RECON_DIFF=TOT_RECON_DIFF+Double.parseDouble(s);
					TOT_RECON=TOT_RECON+Double.parseDouble(s1);
				}

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return TOT_RECON_DIFF;
	}
	public void creatTxtFile(double calculateTOT_RECON_DIFF) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		try {
			out=new PrintWriter(new FileWriter("resources/task1TxtFile.txt"));
			out.println("TOT_RECON_DIFF is: "+calculateTOT_RECON_DIFF);
			System.out.println("File Created at: resources/task1TxtFile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}

	}


	public void DisplayCompName() {
		// TODO Auto-generated method stub
		try {
			b= new BufferedReader(new FileReader("resources/input_file_csv_edited_1.csv"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] arr;
		String line="";
		try {
			while((line=b.readLine())!= null){
				if(line.contains("|")){
					arr=line.split("\\|");
					System.out.print(arr[5]+"\t\t"+arr[1]);
				}
				else if (line.contains(";")) {
					arr=line.split("\\;");
					System.out.print(arr[5]+"\t"+arr[1]);
				}
				else if (line.contains(":")) {
					arr=line.split("\\:");
					System.out.print(arr[5]+"\t"+arr[1]);
				}
				else{
					String name=line.substring(44, line.length()-1);
					String date=line.substring(9, 20);
					System.out.print(name+"\t"+date);
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateExcel(){
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Sheet 1");
		//This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		try {
			b= new BufferedReader(new FileReader("resources/input_file_csv_edited_1.csv"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] arr;
		String line="";
		Integer i=0;
		try {
			while((line=b.readLine())!= null){
				if(line.contains("|")){
					++i;
					arr=line.split("\\|");
					data.put(i.toString(), new Object[] {arr[0], arr[1],arr[2],arr[3],arr[4],arr[5]});
				}
				else if (line.contains(";")) {
					++i;
					arr=line.split("\\;");
					data.put(i.toString(), new Object[] {arr[0], arr[1],arr[2],arr[3],arr[4],arr[5]});
				}
				else if (line.contains(":")) {
					++i;
					arr=line.split("\\:");
					data.put(i.toString(), new Object[] {arr[0], arr[1],arr[2],arr[3],arr[4],arr[5]});
				}
				else{
					++i;
					String s1,s2,s3,s4,s5,s6;
					s1=line.substring(1, 9);
					s2=line.substring(9,20);
					s3=line.substring(20,23);
					s4=line.substring(23,33);
					s5=line.substring(33,44);
					s6=line.substring(44,line.length());
					data.put(i.toString(), new Object[] {s1, s2,s3,s4,s5,s6});
				}

			}
			++i;data.put(i.toString(), new Object[] {"","summary","",TOT_RECON.toString(),TOT_RECON_DIFF.toString()});
			Set<String> keyset = data.keySet();
			int rownum = 0;CellStyle style;
			for (String key : keyset)
			{

				Row row = sheet.createRow(rownum++);
				Object [] objArr = data.get(key);
				int cellnum = 0;
				if(rownum==1){
					style = workbook.createCellStyle();
					style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				}else{
					style = workbook.createCellStyle();
					style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				}
				for (Object obj : objArr)
				{
					Cell cell = row.createCell(cellnum++);
					if(obj instanceof String)
					{
						cell.setCellValue((String)obj);
						cell.setCellStyle(style);}
					else if(obj instanceof Integer){
						cell.setCellValue((Integer)obj);
						cell.setCellStyle(style);}
				}
			}
			try
			{
				//Write the workbook in file system
				FileOutputStream out = new FileOutputStream(new File("resources/Task2Excel.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("Task2Excel.xlsx written successfully on disk at resources/Task2Excel.xlsx");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Iterate over data and write to sheet

	}
	public void genaratePdf() {

		try {
			OutputStream file = new FileOutputStream(new File("resources/Task2Pdf.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(6);


			String line="";
			String[] arr=null;
			int counter=0;
			bpdf=new BufferedReader(new FileReader("resources/input_file_csv_edited_1.csv"));
			while((line=bpdf.readLine())!= null){
				if(line.contains("|")){
					arr=line.split("\\|");
					++counter;
					
					for(int i=0;i<arr.length;i++){
						PdfPCell cell = new PdfPCell(new Phrase(arr[i]));
						if(counter==1){
							cell.setBackgroundColor(BaseColor.GREEN);
							table.addCell(cell);
						}
						else if(Double.parseDouble(arr[4])>3000.0){
							cell.setBackgroundColor(BaseColor.RED);
							table.addCell(cell);
						}
						else{
							cell.setBackgroundColor(BaseColor.GRAY);
							table.addCell(cell);
						}
						
					}


				}
				else if (line.contains(";")) {
					arr=line.split("\\;");
					++counter;
					for(int i=0;i<arr.length;i++){
						PdfPCell cell = new PdfPCell(new Phrase(arr[i]));
						if(counter==1){
							cell.setBackgroundColor(BaseColor.GREEN);
							table.addCell(cell);
						}else if(Double.parseDouble(arr[4])>3000.0){
							cell.setBackgroundColor(BaseColor.RED);
							table.addCell(cell);
						}else{
							cell.setBackgroundColor(BaseColor.GRAY);
							table.addCell(cell);
						}
					}
				}
				else if (line.contains(":")) {

					arr=line.split("\\:");
					++counter;
					for(int i=0;i<arr.length;i++){
						PdfPCell cell = new PdfPCell(new Phrase(arr[i]));
						if(counter==1){
							cell.setBackgroundColor(BaseColor.GREEN);
							table.addCell(cell);
						}else if(Double.parseDouble(arr[4])>3000.0){
							cell.setBackgroundColor(BaseColor.RED);
							table.addCell(cell);
						}else{
							cell.setBackgroundColor(BaseColor.GRAY);
							table.addCell(cell);
						}
					}
				}
				else{
					String s1,s2,s3,s4,s5,s6;
					s1=line.substring(1, 9);
					s2=line.substring(9,20);
					s3=line.substring(20,23);
					s4=line.substring(23,33);
					s5=line.substring(33,44);
					s6=line.substring(44,line.length()-1);
					++counter;
					PdfPCell cell = new PdfPCell(new Phrase(s1));
					PdfPCell cell1 = new PdfPCell(new Phrase(s2));
					PdfPCell cell2 = new PdfPCell(new Phrase(s3));
					PdfPCell cell3 = new PdfPCell(new Phrase(s4));
					PdfPCell cell4 = new PdfPCell(new Phrase(s5));
					PdfPCell cell5 = new PdfPCell(new Phrase(s6));
					if(counter==1){
						cell.setBackgroundColor(BaseColor.GREEN);
						cell1.setBackgroundColor(BaseColor.GREEN);
						cell2.setBackgroundColor(BaseColor.GREEN);
						cell3.setBackgroundColor(BaseColor.GREEN);
						cell4.setBackgroundColor(BaseColor.GREEN);
						cell5.setBackgroundColor(BaseColor.GREEN);
						table.addCell(cell);
						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);
						table.addCell(cell5);
					}else if(Double.parseDouble(arr[4])>3000.0){
						cell.setBackgroundColor(BaseColor.RED);
						cell1.setBackgroundColor(BaseColor.RED);
						cell2.setBackgroundColor(BaseColor.RED);
						cell3.setBackgroundColor(BaseColor.RED);
						cell4.setBackgroundColor(BaseColor.RED);
						cell5.setBackgroundColor(BaseColor.RED);
						table.addCell(cell);
						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);
						table.addCell(cell5);
					}else{
						cell.setBackgroundColor(BaseColor.GRAY);
						cell1.setBackgroundColor(BaseColor.GRAY);
						cell2.setBackgroundColor(BaseColor.GRAY);
						cell3.setBackgroundColor(BaseColor.GRAY);
						cell4.setBackgroundColor(BaseColor.GRAY);
						cell5.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);
						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);
						table.addCell(cell5);
					}
				}
			}
			PdfPCell cell = new PdfPCell(new Phrase("summary"));
			PdfPCell cell1 = new PdfPCell(new Phrase(" "));
			PdfPCell cell2 = new PdfPCell(new Phrase(TOT_RECON.toString()));
			PdfPCell cell3 = new PdfPCell(new Phrase(TOT_RECON_DIFF.toString()));
			table.addCell(cell);
			table.addCell(cell1);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			
			table.setSpacingBefore(3.0f); // Space Before table starts, like
			// margin-top in CSS
			table.setSpacingAfter(3.0f); // Space After table starts, like
			// margin-Bottom in CSS

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........


			document.add(new Paragraph(""));
			document.add(new Paragraph(""+ new Date().toString()));

			document.add(table);
			document.close();

			file.close();

			System.out.println("Task2Pdf.Pdf written successfully on disk at resources/Task2Pdf.pdf..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
