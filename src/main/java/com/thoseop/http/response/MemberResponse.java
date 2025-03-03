package com.thoseop.http.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MemberResponse implements Serializable{

    private static final long serialVersionUID = 4316271849331986379L;

    private Long memberId;
    private String memberName;
    private String memberEmail;
}
