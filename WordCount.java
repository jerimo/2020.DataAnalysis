package kc.ar.sejong.da.prj3;

public class WordCount {

	private String word;

	private Integer count;

	public String getWord() {

		return word;

	}

	public void setWord(String word) {

		this.word = word;

	}

	public Integer getCount() {

		return count;

	}

	public void setCount(Integer count) {

		this.count = count;

	}

	public WordCount(String word, Integer count) {

		super();

		this.word = word;

		this.count = count;

	}

}
