package extractor.bifurcation.structure;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import extractor.bifurcation.utils.Neighborhood;

/**
* This class the extraction and counting of the amount of bifurcation of the image is performed. 
* The count is performed based on the amount of neighbors of a voxel of the image. The neighbor check function is 
* responsible for identifying the neighbors of a given voxel.
* 
* @author Misael Jr
*
*/

public class Bifurcation {

	ArrayList<Double[]> bifurc_points = new ArrayList<Double[]>();

	ArrayList<Double[]> neighbors;
	ArrayList<Double[]> neighborTemp;

	Neighborhood neighbor;

	/**
	 * Function that measures the number of bifurcation
	 * @param data -- Matrix with image points.
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Number of image segments
	 * @throws FileNotFoundException
	 */
	
	public double extractorCountBifurc(double[][][][] data, int nx, int ny, int nz, int dim) throws FileNotFoundException{
		int quantBifurc = 0;
		neighbor = new Neighborhood();
		//double bifurc_normalized = 0;
		
		for (int d = 0; d < dim; d++){
			for (int i = 0; i < nx; i++) {
				for (int j = 0; j < ny; j++){
					for (int k = 0; k < nz; k++){

						if (data[i][j][k][d] != 0){

							Double[] points = {(double) i, (double) j, (double) k};
							
							neighbors = neighbor.checkNeighbors26(data, bifurc_points, i, j, k, d, nx, ny, nz, dim);

							if(neighbors.size() > 2){
								quantBifurc++;
								bifurc_points.add(points); // if it is bifurcation point, add in the array of bifurcation points
							}else{
								continue;
							}
						}else
							continue;
					}
				}
			}
		}
		
		//bifurc_normalized = getNormalized(quantBifurc);
		
		return quantBifurc;

	}
	
	/**
	 * set the normalization according to the base of max and min
	 * @param bifurcation
	 * @return Normalized value
	 */
	/*public double getNormalized(double bifurcation){
		
		double normal_value = 0;
		
		normal_value = (bifurcation - min)/(max - min);
		
		return normal_value;
		
	}*/

}
