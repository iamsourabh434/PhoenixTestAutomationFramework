package com.api.constant;

public enum ServiceLocation {
	Service_Location_A(1), Service_Location_B(2), Service_Location_C(3);

	int code;

	private ServiceLocation(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
