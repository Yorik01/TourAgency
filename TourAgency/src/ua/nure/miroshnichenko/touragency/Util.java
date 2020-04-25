package ua.nure.miroshnichenko.touragency;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Util {

	private static Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	private Util() {
	}
	
	public static boolean isNumber(String str) {
		if(str == null) {
			return false;
		}
		return numberPattern.matcher(str).matches();
	}
	
	public static String listToString(List<?> list) {
		StringBuilder result = new StringBuilder();
		
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()) {
			Object element = iterator.next();
			boolean isNumber = isNumber(element.toString());
			
			if (!isNumber) {
				result.append('\'');
			}

			result.append(element.toString());
			
			if (!isNumber) {
				result.append('\'');
			}
			
			if(!iterator.hasNext()) {
				break;
			}
			result.append(',');
		}
		return result.toString();
	}
	
	public static String mergeStrings(String[] strings, String delimiter) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			res.append(strings[i]);
			
			if (i != strings.length - 1) {
				res.append(delimiter);
			}
		}
		return res.toString();
	}
	
	public static String enumListToOrdinalString(List<? extends Enum> list) {
		StringBuilder result = new StringBuilder();

		Iterator<? extends Enum> iterator = list.iterator();
		while(iterator.hasNext()) {
			Enum en = iterator.next();
			result.append(en.ordinal() + 1);
			if(!iterator.hasNext()) {
				break;
			}
			result.append(',');
		}
		return result.toString();
	}
}
