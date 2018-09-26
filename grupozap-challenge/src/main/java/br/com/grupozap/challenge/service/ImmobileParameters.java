package br.com.grupozap.challenge.service;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ImmobileParameters {

	private final int page;
	private final String portal;

}
