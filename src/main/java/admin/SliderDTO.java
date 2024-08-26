package admin;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SliderDTO {
	private int sIdx;
	private String sName;
	private String sFile;
	private String sFileO;
	private Date sRegdate;
	private Date sUpdate;
	
	private List<Object> sliderData;
}
