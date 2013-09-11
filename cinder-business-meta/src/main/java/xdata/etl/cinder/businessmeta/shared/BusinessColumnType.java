/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public interface BusinessColumnType {
	public static class Names {
		public static final String SIMPLE = "SIMPLE";
		public static final String SUB_MAPPING = "SUB_MAPPING";
	}

	public enum BusinessColumnTypeEnum {
		SIMPLE, SUB_MAPPING;
	}

	BusinessColumnTypeEnum getType();

	public static class SimpleBusinessColumnType implements BusinessColumnType {

		@Override
		public BusinessColumnTypeEnum getType() {
			return BusinessColumnTypeEnum.SIMPLE;
		}

	}

	public static class SubMappingBusinessColumnType implements
			BusinessColumnType {
		@Override
		public BusinessColumnTypeEnum getType() {
			return BusinessColumnTypeEnum.SUB_MAPPING;
		}
	}
}
