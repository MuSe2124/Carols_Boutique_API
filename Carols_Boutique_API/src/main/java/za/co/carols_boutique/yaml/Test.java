package za.co.carols_boutique.yaml;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		CarolsYAML c = new CarolsYAML();
		System.out.println(c.getUrl());
		//System.out.println(c.getPassword());
		//System.out.println(c.getUsername());
	}
}
