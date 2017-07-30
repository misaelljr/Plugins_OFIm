package extractor.avgseg.structure;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import extractor.avgseg.utils.Neighborhood;

/**
 * This class is performed extracting and counting the number of bifurcations and end-points of the blood vessel. 
 * The counting is performed based on the amount of neighbors of a voxel by measuring the amount of fork ends. 
 * Thus, this class contains the function responsible in verifying voxel by voxel the amount of neighbors and 
 * classifying as a bifurcation point, end-points or line point. Based on the extraction of the number of 
 * bifurcation points and end-points, the image segments are counted.
 * 
 * @author Misael Jr
 *
 */

public class Bifurc_Seg {

	ArrayList<Double[]> bifurc_points = new ArrayList<Double[]>();
	ArrayList<Double[]> end_points = new ArrayList<Double[]>();
	ArrayList<Double[]> line_point = new ArrayList<Double[]>();

	ArrayList<Double[]> neighbors;

	Neighborhood neighbor;
	Count_Segmentos countSeg;
	
	int quantBifurc = 0;
	int quant_endpoints = 0;
	int quantLine = 0;
	
	/**
	 * Function that measures the average size of segments from the identification of the number of segments.
	 * @param data -- Matrix with image points.
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Number of image segments
	 * @throws FileNotFoundException
	 */
	public double extratorAVGSeg(double[][][][] data, int nx, int ny, int nz, int dim) throws FileNotFoundException{
		
		neighbor = new Neighborhood();
		countSeg = new Count_Segmentos();
		double quant_seg = 0;

		for (int d = 0; d < dim; d++){
			for (int i = 0; i < nx; i++) {
				for (int j = 0; j < ny; j++){
					for (int k = 0; k < nz; k++){

						if (data[i][j][k][d] != 0){

							Double[] points = {(double) i, (double) j, (double) k};
							
							neighbors = neighbor.checkNeighbors26(data, bifurc_points, i, j, k, d, nx, ny, nz, dim);

							if(neighbors.size() > 2){
								quantBifurc++;
								bifurc_points.add(points);
							}else if (neighbors.size() == 1){
								quant_endpoints++;
								end_points.add(points); 
							}else if (neighbors.size() == 2)
								quantLine++;
						}else
							continue;
					}
				}
			}
		}	
		
		//Number of image segments
		quant_seg = countSeg.extractorAVGseg(data, bifurc_points, end_points, nx, ny, nz, dim);
				
		return quant_seg;

	}

}
