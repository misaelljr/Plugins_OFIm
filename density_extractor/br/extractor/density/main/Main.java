package extractor.density.main;

import extractor.density.structure.DensityExtractor;


public class Main {

	public static void main(String[] args) throws Exception {
		DensityExtractor dens = new DensityExtractor();
		double density;
		String name;
		String [] file = {"test/aneurism1sigm1_esqueleto.nii"};				
		
		density = dens.computeValue(file);
		name = dens.getName();
		
		System.out.println(name + " " + "=" +" " +density);

	}
}