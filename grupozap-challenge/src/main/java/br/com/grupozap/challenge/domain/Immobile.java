package br.com.grupozap.challenge.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;




@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@JsonDeserialize(builder = Immobile.ImmobileBuilder.class)
public class Immobile {

	private final String id;
	private final int usableAreas;
	private final String listingType;
	private final Date createdAt;
	private final String listingStatus;
	private final int parkingSpaces;
	private final Date updatedAt;
	private final boolean owner;
	private final List<String> images;
	private final Address address;
	private final int bathrooms;
	private final int bedrooms;
	private final PricingInfos pricingInfos;

	@JsonPOJOBuilder(withPrefix="")
	public static final class ImmobileBuilder{}

}
