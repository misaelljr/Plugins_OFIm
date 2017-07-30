package extractor.bifurcation.main;

import extractor.bifurcation.structure.BifurcationExtractor;

public class Main {

	public static void main(String[] args) throws Exception {
		BifurcationExtractor bifurc_extract = new BifurcationExtractor();
		double bifurcation;
		String name;
		String [] file = {"test/aneurism1sigm1_esqueleto.nii"};				
		
		bifurcation = bifurc_extract.computeValue(file);
		name = bifurc_extract.getName();
		
		System.out.println(name + " " + "=" +" " +bifurcation);

	}
}