package umesh_FirstDay;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOperations fo=new FileOperations();
		FileOperations.openFile();
		fo.creatTxtFile(fo.calculateTOT_RECON_DIFF());
		System.out.println("TOT_RECON_DIFF is: "+fo.calculateTOT_RECON_DIFF());
		fo.DisplayCompName();
		fo.generateExcel();
		fo.genaratePdf();
	}

}
