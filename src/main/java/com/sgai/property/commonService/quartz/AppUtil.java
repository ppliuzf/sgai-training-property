package com.sgai.property.commonService.quartz;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * The Class AppUtil.
 *
 * @author pavan.solapure
 */
public class AppUtil {

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Checks if is collection empty.
	 *
	 * @param collection the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
	
	/**
	 * Checks if is object empty.
	 *
	 * @param object the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(Object object) {
		if(object == null){ return true;}
		else if(object instanceof String) {
            return ((String) object).trim().length() == 0;
		} else if(object instanceof Collection) {
			return isCollectionEmpty((Collection<?>)object);
		}
		return false;
	}
	
	/**
	 * Gets the bean to json string.
	 *
	 * @param beanClasses the bean classes
	 * @return the bean to json string
	 */
	public static String getBeanToJsonString(Object... beanClasses) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object beanClass : beanClasses) {
			stringBuilder.append(getBeanToJsonString(beanClass));
			stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}
	
	/**
	 * Concatenate.
	 *
	 * @param listOfItems the list of items
	 * @param separator the separator
	 * @return the string
	 */
	public String concatenate(List<String> listOfItems, String separator) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> stit = listOfItems.iterator();
		
		while (stit.hasNext()) {
			sb.append(stit.next());
			if(stit.hasNext()) {
				sb.append(separator);
			}
		}
		
		return sb.toString();		
	}
	
}
