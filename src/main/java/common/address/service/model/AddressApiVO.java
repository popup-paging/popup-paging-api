package common.address.service.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressApiVO {
	
	private String sido;
	private String district;
	private String city;
	private String dong;
	private String bunji;
	private String zipcode;
	private String gugun;

	private String count;
	private int rowNum;
	private int firstIndex;
	
}