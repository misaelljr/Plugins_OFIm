package extractor.segments.main;

import extractor.segments.structure.SegmentosExtractor;


public class Main {

	public static void main(String[] args) throws Exception {
		SegmentosExtractor seg = new SegmentosExtractor();
		double segments;
		String name;
		String [] file = {"test/aneurism1sigm1_esqueleto.nii"};				
		
		segments = seg.computeValue(file);
		name = seg.getName();
		
		System.out.println(name + " " + "=" +" " +segments);

	}
}