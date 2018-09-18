package dto;

import java.io.Serializable;

public class ReviewDto extends OrderDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4992087031238518511L;
	
	private int score;
	private String review;
	
	public ReviewDto(String title, String type, String location, String consumerId, String date, int score,
			String review) {
		super(title, type, location, consumerId, date);
		this.score = score;
		this.review = review;
	}
	
	public ReviewDto(String title, String type, String location, String consumerId, int score, String review) {
		super(title, type, location, consumerId);
		this.score = score;
		this.review = review;
	}
	
	public ReviewDto(OrderDto oDto, int score, String review) {
		super(oDto);
		this.score = score;
		this.review = review;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewDto [score=" + score + ", review=" + review + "]";
	}

	
	
}
