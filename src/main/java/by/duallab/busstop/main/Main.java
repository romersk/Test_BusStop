package by.duallab.busstop.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import by.duallab.busstop.files.FilesTest;

public class Main {

	public static void main(String[] args) throws IOException {

		String directory;
		directory = checkDir(); //Enter Directory
		
		FilesTest taskFiles = new FilesTest(directory);

		taskFiles.solveTask();
	}

	private static String checkDir() {
		String directory = null;

		System.out.println("Enter directory >> ");
		Scanner scanString = new Scanner(System.in);
		
		while (scanString.hasNextLine()) {

			directory = scanString.nextLine();

			Path file = Paths.get(directory); 
			boolean isExist = Files.exists(file); //is Path real?

			if (isExist) {
				break;
			} else {
				System.out.println("Enter directory >> ");
			}
		}
		scanString.close();
		return directory;
	}

}
