package extractor.endpoints.main;

import extractor.endpoints.structure.EndPointsExtractor;


public class Main {

	public static void main(String[] args) throws Exception {
		EndPointsExtractor endpoints_extract = new EndPointsExtractor();
		double endpoints;
		String name;
		String [] file = {"test/aneurism1sigm1_esqueleto.nii"};				
		
		endpoints = endpoints_extract.computeValue(file);
		name = endpoints_extract.getName();
		
		System.out.println(name + " " + "=" +" " +endpoints);

	}
}