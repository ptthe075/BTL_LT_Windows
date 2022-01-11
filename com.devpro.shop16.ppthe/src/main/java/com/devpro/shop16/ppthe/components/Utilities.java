package com.devpro.shop16.ppthe.components;

public class Utilities {

	public static long getKilobytes(long bytes) {
		long kilobytes = (bytes / 1024);
		return kilobytes;
	}

	public static long getMegabytes(long bytes) {
		long megabytes = (getKilobytes(bytes) / 1024);
		return megabytes;
	}
}
