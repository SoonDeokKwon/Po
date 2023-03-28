package co.prod.vo;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int replyNo;
	private String replyWriter;
	private String replyContent;
	private String replyDate;
	private String productCode;
	
}
