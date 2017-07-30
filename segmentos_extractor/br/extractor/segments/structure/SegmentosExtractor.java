package extractor.segments.structure;
import java.io.IOException;
import extractor.segments.nifti_reader.NiftiVolume;
import br.oracle.pluginInterfaces.InterfaceImageExtractor;
import br.oracle.pluginInterfaces.OFImPlugin;

public class SegmentosExtractor extends OFImPlugin implements InterfaceImageExtractor{
	
	private final String name = "SegmentosExtractor";
	String [] file;
	Bifurc_Seg bifurc;
	double quant_segments = 0;
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double computeValue(Object complexObject) throws IOException {

		this.file = (String[]) complexObject;
		bifurc = new Bifurc_Seg();
		

		if (file.length == 0 || file.length > 2){
			return 0;
		}

		NiftiVolume volume = NiftiVolume.read(file[0]);
		int nx = (int) (volume.header.dim[1] * volume.header.pixdim[1]);
		int ny = (int) (volume.header.dim[2] * volume.header.pixdim[2]);
		int nz = (int) (volume.header.dim[3] * volume.header.pixdim[3]);
		int dim = volume.header.dim[4];
				
		if (dim == 0)
			dim = 1;

		if (file.length == 1){ 			
			quant_segments = bifurc.extratorCountBifurc(volume.data, nx, ny, nz, dim);
		}else{
			volume.write(file[1]);
		}		
		
		return quant_segments;
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
