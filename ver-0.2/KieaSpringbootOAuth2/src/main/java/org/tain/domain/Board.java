package org.tain.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.tain.domain.enums.BoardType;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@NoArgsConstructor
@Data
public class Board {

	@Column
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String subTitle;
	
	@Column
	private String content;
	
	@Column
	@Enumerated(EnumType.STRING)
	private BoardType boardType;
	
	@Column
	private LocalDateTime createdDate;
	
	@Column
	private LocalDateTime updatedDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Builder
	public Board(
			String title,
			String subTitle,
			String content,
			BoardType boardType,
			LocalDateTime createdDate,
			LocalDateTime updatedDate,
			User user
			) {
		this.title = title;
		this.subTitle = subTitle;
		this.content = content;
		this.boardType = boardType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
	}
}