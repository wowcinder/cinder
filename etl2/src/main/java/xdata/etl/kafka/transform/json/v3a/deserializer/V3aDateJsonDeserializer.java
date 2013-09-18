package xdata.etl.kafka.transform.json.v3a.deserializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class V3aDateJsonDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		if (json.getAsString().matches("^\\d+$")) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(Long.parseLong(json.getAsString()));
			return c.getTime();
		} else {
			Pattern pattern = Pattern
					.compile("^\\d{4}\\-\\d{2}-\\d{2}[\\s/]\\d{2}:\\d{2}:\\d{2}");
			Matcher matcher = pattern.matcher(json.getAsString());
			String dateStr = null;
			if (matcher.find()) {
				dateStr = matcher.group();
			}
			if (dateStr == null) {
				return null;
			}
			dateStr = dateStr.replace("/", " ");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

	}
}
