package xdata.etl;

import java.util.Collection;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		System.out.println(upperCaseFirstLetter("_someFieldName"));
	}

	private static String upperCaseFirstLetter(String name) {
		StringBuilder fieldNameBuilder = new StringBuilder();
		int index = 0;
		char firstCharacter = name.charAt(index);

		while (index < name.length() - 1) {
			if (Character.isLetter(firstCharacter)) {
				break;
			}

			fieldNameBuilder.append(firstCharacter);
			firstCharacter = name.charAt(++index);
		}

		if (index == name.length()) {
			return fieldNameBuilder.toString();
		}

		if (!Character.isUpperCase(firstCharacter)) {
			String modifiedTarget = modifyString(
					Character.toUpperCase(firstCharacter), name, ++index);
			return fieldNameBuilder.append(modifiedTarget).toString();
		} else {
			return name;
		}
	}

	private static String modifyString(char firstCharacter, String srcString,
			int indexOfSubstring) {
		return (indexOfSubstring < srcString.length()) ? firstCharacter
				+ srcString.substring(indexOfSubstring) : String
				.valueOf(firstCharacter);
	}
}
