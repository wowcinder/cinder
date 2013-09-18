package xdata.etl.kafka.transform.json.v3a.shadow;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.entity.v3a.response.V3aOrderPriceQueryResponse;
import xdata.etl.entity.v3a.srv.V3aOrderPriceQueryFilmPrice;

import com.google.gson.annotations.SerializedName;

public class V3aOrderPriceQueryResponseShadow {
	@SerializedName("Count")
	private Integer count;
	@SerializedName("FilmPrice")
	private FilePriceInfo filmPrices = new FilePriceInfo();

	public V3aOrderPriceQueryResponseShadow() {
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<V3aOrderPriceQueryFilmPrice> getFilmPrices() {
		return filmPrices;
	}

	public void setFilmPrices(FilePriceInfo filmPrices) {
		this.filmPrices = filmPrices;
	}

	public V3aOrderPriceQueryResponse getResponse() {
		V3aOrderPriceQueryResponse response = new V3aOrderPriceQueryResponse();
		response.setCount(getCount());
		return response;
	}

	public static class FilePriceInfo extends
			ArrayList<V3aOrderPriceQueryFilmPrice> {
		private static final long serialVersionUID = -6395971963307031168L;

	}

}
