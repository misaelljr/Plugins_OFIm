package extractor.avgseg.main;

import extractor.avgseg.structure.AVG_Size_Seg_Extractor;


public class Main {

	public static void main(String[] args) throws Exception {
		AVG_Size_Seg_Extractor seg = new AVG_Size_Seg_Extractor();
		double segments;
		String name;
		String [] file = {"test/aneurism1sigm1_esqueleto.nii"};				
		
		segments = seg.computeValue(file);
		name = seg.getName();
		
		System.out.println(name + " " + "=" +" " +segments);

	}
}