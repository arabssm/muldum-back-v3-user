package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements Serializable {
    private Integer classNo;
    private Integer grade;
    private Integer number;
    // 추가 필드는 필요에 따라 확장 가능
}
