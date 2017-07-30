package extractor.endpoints.structure;

import java.io.IOException;
import java.io.PrintWriter;

import extractor.endpoints.nifti_reader.NiftiVolume;
import br.oracle.pluginInterfaces.InterfaceImageExtractor;
import br.oracle.pluginInterfaces.OFImPlugin;

public class EndPointsExtractor extends OFImPlugin implements InterfaceImageExtractor{
	
	private final String name = "EndPointsExtractor";
	String [] file;
	EndPoints endpoints;

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public double computeValue(Object complexObject) throws IOException {

		this.file = (String[]) complexObject;
		endpoints = new EndPoints();
		double quant_endpoints = 0;
		

		if (file.length == 0 || file.length > 2)
		{
			System.out.println("Usage: niftijio input.nii.gz [output]");
			System.out.println("Description: read a volume and optionally write it out again");
			return 0;
		}

		NiftiVolume volume = NiftiVolume.read(file[0]);

		int nx = (int) (volume.header.dim[1] * volume.header.pixdim[1]);
		int ny = (int) (volume.header.dim[2] * volume.header.pixdim[2]);
		int nz = (int) (volume.header.dim[3] * volume.header.pixdim[3]);
		int dim = volume.header.dim[4];
				
		if (dim == 0)
			dim = 1;

		if (file.length == 1)
		{
		    			
			quant_endpoints = endpoints.extratorCountBifurc(volume.data, nx, ny, nz, dim);

		}

		else if (file[1].endsWith("txt"))
		{
			PrintWriter out = new PrintWriter(file[1]);

			out.println("volume ");
			out.println("dimensions:");
			out.println(nx + " " + ny + " " + nz + " " + dim);
			out.println("data:");
			
			for (int d = 0; d < dim; d++)
				for (int k = 0; k < nz; k++)
					for (int j = 0; j < ny; j++)
						for (int i = 0; i < nx; i++)
							
							out.println(volume.data[i][j][k][d]);

			out.println();
			out.close();
		}

		else
		{
			volume.write(file[1]);
		}

			
		return quant_endpoints;
	}

	@Override
	public void setProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
