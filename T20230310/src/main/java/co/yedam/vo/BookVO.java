package co.yedam.vo;

import lombok.Data;

@Data
public class BookVO {
	private String bookCode;
	private String bookTitle;
	private String bookAuthor;
	private String bookPress;
	private String bookDesc;
	private int bookPrice;
}
