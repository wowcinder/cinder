package xdata.etl.hbase.annotatins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import xdata.etl.hbase.row.HbaseDefaultRowGenerator;
import xdata.etl.hbase.row.HbaseRowGenerator;

/**
 * HbaseTable
 * 
 * @author XuehuiHe
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HbaseTable {
	/**
	 * 表名
	 * 
	 * @return
	 */
	String name();

	/**
	 * row生成工具类
	 * 
	 * @return
	 */
	Class<? extends HbaseRowGenerator> rowGenerator() default HbaseDefaultRowGenerator.class;
}
