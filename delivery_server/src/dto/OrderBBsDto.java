package dto;

public class OrderBBsDto extends OrderDto {	
	
	public static final int REQUEST = 1;
	public static final int ONGOING = 2;
	public static final int DONE = 3;
	public static final int CANCELED = 4;
	
	private String contents;
	private int price;
	private String[] applicants;
	private int status;
	
	public OrderBBsDto(int reqNum, String title, String type, String location, String consumerId, String delivererId,
			String date) {
		super(reqNum, title, type, location, consumerId, delivererId, date);
		
	}
	
	public OrderBBsDto(OrderDto oDto) {
		super(oDto);
		// TODO Auto-generated constructor stub
	}	
}
