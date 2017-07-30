package extractor.segments.structure;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import extractor.segments.utils.Neighborhood;

/**
* This class, the extraction and counting of the amount of bifurcation, endpoints and line points of the image is performed. 
* The count is performed based on the amount of neighbors of a voxel of the image. The neighbor check function is 
* responsible for identifying the neighbors of a given voxel.
* 
* @author Misael Jr
*
*/

public class Bifurc_Seg {

	ArrayList<Double[]> bifurc_points = new ArrayList<Double[]>();
	ArrayList<Double[]> endpoints = new ArrayList<Double[]>();
	ArrayList<Double[]> line_points = new ArrayList<Double[]>();

	ArrayList<Double[]> neighbors;
	ArrayList<Double[]> neighborTemp;

	Neighborhood neighbor;
	Count_Segmentos countSeg;
	
	int quantBifurc = 0;
	int quantEnd = 0;
	int quantLine = 0;
	
	/**
	 * Function that measures the number of bifurcation, endpoints and line points
	 * @param data -- Matrix with image points.
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Number of image segments
	 * @throws FileNotFoundException
	 */

	public double extratorCountBifurc(double[][][][] data, int nx, int ny, int nz, int dim) throws FileNotFoundException{
		
		neighbor = new Neighborhood();
		countSeg = new Count_Segmentos();
		//double seg_normalized = 0;
		double quantSeg = 0;

		for (int d = 0; d < dim; d++){
			for (int i = 0; i < nx; i++) {
				for (int j = 0; j < ny; j++){
					for (int k = 0; k < nz; k++){

						if (data[i][j][k][d] != 0){ //pegar o primeiro ponto da imagem, ou seja, > 0

							Double[] points = {(double) i, (double) j, (double) k};
							
							neighbors = neighbor.checkNeighbors26(data, bifurc_points, i, j, k, d, nx, ny, nz, dim);

							if(neighbors.size() > 2){
								quantBifurc++;
								bifurc_points.add(points);
							}else if (neighbors.size() == 1){
								quantEnd++;
								endpoints.add(points); 
							}else if (neighbors.size() == 2)
								quantLine++;
						}else
							continue;
					}
				}
			}
		}	
		
		//Number of image segments
		quantSeg = countSeg.extratorCountSeg(data, bifurc_points, endpoints, nx, ny, nz, dim);
				
		return quantSeg;

	}
	
	/**
	 * set the normalization according to the base of max and min
	 * @param segments
	 * @return Normalized value
	 */
	/*public double getNormalized(double segments){
		
		double normal_value = 0;
		
		normal_value = (segments - min)/(max - min);
		
		return normal_value;
		
	}*/

}
