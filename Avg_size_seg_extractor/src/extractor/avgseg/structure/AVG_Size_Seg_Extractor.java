package extractor.avgseg.structure;
import java.io.IOException;
import extractor.avgseg.nifti_reader.NiftiVolume;
import br.oracle.pluginInterfaces.InterfaceImageExtractor;
import br.oracle.pluginInterfaces.OFImPlugin;

public class AVG_Size_Seg_Extractor extends OFImPlugin implements InterfaceImageExtractor{
	
	private final String name = "AVG_Size_Seg_Extractor";
	String [] file;
	Bifurc_Seg bifurc;
	double avg_size_seg;
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double computeValue(Object complexObject) throws IOException {

		this.file = (String[]) complexObject;
		bifurc = new Bifurc_Seg();
		double avg_size_seg = 0;
		
		if (file.length == 0 || file.length > 2){ return 0; }

		NiftiVolume volume = NiftiVolume.read(file[0]);
		int nx = (int) (volume.header.dim[1] * volume.header.pixdim[1]);
		int ny = (int) (volume.header.dim[2] * volume.header.pixdim[2]);
		int nz = (int) (volume.header.dim[3] * volume.header.pixdim[3]);
		int dim = volume.header.dim[4];
				
		if (dim == 0)
			dim = 1;

		if (file.length == 1) {			
			avg_size_seg =  bifurc.extratorAVGSeg(volume.data, nx, ny, nz, dim); 
		} else {
			volume.write(file[1]);
		}		
		
		return avg_size_seg;
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
