package com.api.constant;

public enum Product {
	NEXUS_2(1), PIXEL(2);
	int code;

	private Product(int code) { // in ENUM private is implicitly private. Even if I remove private keyword it will be private
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
