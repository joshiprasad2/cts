package org.eauction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserToken {

	private String userId;
	private String accessToken;
	private String refreshToken ;
}
