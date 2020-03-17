package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum TransportType {
	AIRPLANE, BUS;

	public static List<TransportType> getTransportTypes(String[] names) {
		List<TransportType> transportTypes = new ArrayList<>();
		for (String name : names) {
			transportTypes.add(valueOf(name));
		}
		return transportTypes;
	}
}
