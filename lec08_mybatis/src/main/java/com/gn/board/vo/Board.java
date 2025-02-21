package com.gn.board.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor //기본 생성자.
@AllArgsConstructor // 매개 변수 생성자.
@Getter // getter
@Setter	// setter
@ToString // toString
@Builder // 매개변수 생성자를 입맛대로 조절할 수 있다?
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private String memberName;
	private String orderType;
	
}
