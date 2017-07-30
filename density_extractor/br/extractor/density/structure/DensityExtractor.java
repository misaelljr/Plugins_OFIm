package extractor.density.structure;
import java.io.IOException;
import extractor.density.nifti_reader.NiftiVolume;
import br.oracle.pluginInterfaces.InterfaceImageExtractor;
import br.oracle.pluginInterfaces.OFImPlugin;

public class DensityExtractor extends OFImPlugin implements InterfaceImageExtractor{
	
	private final String name = "DensityExtractor";
	String [] file;
	Density densi;
	double value_density = 0;
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public double computeValue(Object complexObject) throws IOException {
		
		this.file = (String[]) complexObject;
		densi = new Density();		

		if (file.length == 0 || file.length > 2) { return 0; }

		NiftiVolume volume = NiftiVolume.read(file[0]);
		int nx = (int) (volume.header.dim[1] * volume.header.pixdim[1]);
		int ny = (int) (volume.header.dim[2] * volume.header.pixdim[2]);
		int nz = (int) (volume.header.dim[3] * volume.header.pixdim[3]);
		int dim = volume.header.dim[4];
				
		if (dim == 0)
			dim = 1;

		if (file.length == 1){			
			value_density = densi.extractorDensi(volume.data, nx, ny, nz, dim);
		} else{
			volume.write(file[1]);
		}

		return value_density;
	}

	@Override
	public void setProperty(String propertyName, Object propertyValue) { }

	@Override
	public Object getProperty(String propertyName) { return null; }

	@Override
	public Object[] getProperties() { return null; }

	@Override
	public String[] getPropertyNames() { return null; }

}
