package org.conan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
	private String keyword;
	private String type;
	public String[] getTypeArr() {//검색 조건을 배열로 만들어 한꺼번에 처리 MyBatis는 java Beans의 규칙을 엄격하게 지키지 않음
	    return type==null? new String[] {}:type.split("");
	}
}
