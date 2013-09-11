/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public interface BusinessType {

	public static class Names {
		public static final String C_TYPE = "C_TYPE";
		public static final String JSON_TYPE = "JSON_TYPE";
	}

	public enum BusinessTypeEnum {
		C_TYPE, JSON_TYPE;
	}

	BusinessTypeEnum getType();

	public static class BusinessCType implements BusinessType {

		@Override
		public BusinessTypeEnum getType() {
			return BusinessTypeEnum.C_TYPE;
		}

	}

	public static class BusinessJsonType implements BusinessType {

		@Override
		public BusinessTypeEnum getType() {
			return BusinessTypeEnum.JSON_TYPE;
		}

	}

}
